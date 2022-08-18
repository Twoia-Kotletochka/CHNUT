import socket
import threading
import random
import math
import time
import json

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
Dict_data = {}
MaxSizeofUsers = 9

def run():
    while True:
        socket_user, adress_user = server.accept()
        userID = socket_user.recv(2048).decode()

        if(CheckID(userID)):
            print(userID)
            print(socket_user, " connected")
            rnd_value = (random.randint(0, 20))
            socket_user.send(str(rnd_value).encode())

            #Варіант 8
            result = int(userID)*math.sin(1/rnd_value)
            #Варіант 8

            print("result = ", result)
            UserY = float(socket_user.recv(2048).decode())

            if(result == UserY):
                socket_user.send("Connected".encode())
                login = getUserLogin(userID)
                f = open('result.txt', 'a')
                f.write(str(userID)+'\t'+login+'\t'+time.ctime()+'\t'+"connect"+'\t\t'+"online"+'\n')
                f.close()
                thread_for_listen = threading.Thread(target=ping_user, args=(socket_user, userID, login,))
                thread_for_listen.start()
                thread_for_listen = threading.Thread(target=getDataFrmUser, args=(socket_user,))
                thread_for_listen.start()

        else:
            socket_user.send("Your ID is not registered!".encode())
            socket_user.close()


def getUserLogin(id):
    with open('user.txt', 'r') as json_file:
        obj = json.load(json_file)
        for i in obj['users']:
            if (i['ID'] == id):
                return i['login']

def getDataFrmUser(user):
    while True:
        try:
            data = user.recv(2048)
            data = data.decode()
            print(data)
        except:
            pass

def ping_user(user, userID, login):
    while True:
        time.sleep(2)
        try:
            user.sendall(b"ping")
        except:
            user.close()
            f = open('result.txt', 'a')
            f.write(str(userID) + '\t' + login + '\t' + time.ctime() + '\t' + "disconnect" + '\t' + "offline" + '\n')
            f.close()
            break

def getSizeofUsers():
    count = 0
    with open('user.txt', 'r') as json_file:
        obj = json.load(json_file)
        for i in obj['users']:
            count+=1
    return count

def CheckID(id):
    with open('user.txt', 'r') as json_file:
        obj = json.load(json_file)
        for i in obj['users']:
            if (i['ID'] == id):
                return True
    return False


def CreateFile():
    f = open('result.txt', 'w')
    f.write('ID\tLOGIN\tDATE\t\t\t\tOPERATION\tSTATUS\n')
    f.close()

def CheckSizeJson():
    with open('user.txt', 'r') as json_file:
        try:
            obj = json.load(json_file)
            if(obj == None):
                print("Null")
        except:
            print("mu;;")

def isFileExist():
    with open('user.txt', 'r') as json_file:
        try:
            obj = json.load(json_file)
        except:
            Dict_data['users'] = []


if __name__ == '__main__':
    CreateFile()
    isFileExist()
    server.bind(("127.0.0.1", 5555))
    server.listen(MaxSizeofUsers)
    run()