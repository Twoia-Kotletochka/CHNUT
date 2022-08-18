import socket
import threading
import random
import math
import time
import json

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
Dict_data = {}
MaxSizeofUsers = 8
Stop_thread = False
Thread_control_dict = {}
IsConnectSocket = {}
NumberOfQuestions = 4

def Autentification(user, userID, login):
    print("a1")
    for i in range(NumberOfQuestions):
        print("a2")
        count = 0
        rnd_value = (random.randint(0, 12)) #Кількість запитань
        f = open('questions.txt', 'r')
        for line in f:
            count +=1
            if(count == rnd_value):
                user.send(b'auth')
                user.send(line.encode())
                result = eval(line)
                try:
                    us_result = int(user.recv(2048).decode())
                    print(user, "answer", us_result)
                    if (result == us_result):
                        print(user, i + 1, "Authentication passed")
                        user.send(str(i + 1).encode() + b' Authentication passed')
                        if(i == 4):
                            LoginLen = len(login)
                            SpaceStr = ''
                            if (LoginLen > 5):
                                NumSpaces = 8 - (LoginLen - 5)
                                for i in range(NumSpaces):
                                    SpaceStr = SpaceStr + ' '
                            f = open('logs.txt', 'a')
                            f.write(str(userID) + '\t' + login + SpaceStr + time.ctime() + '\t' + "Connected " + '\t' + "online" + '\t' 'Authentication passed successfully' + '\n')
                            f.close()
                    if (result != us_result):
                        CloseConnection(user=user, userID=userID, login=login)
                        IsConnectSocket[user] = False
                        return
                except:
                    CloseConnection(user=user, userID=userID, login=login)
                    IsConnectSocket[user] = False
                    return
        f.close()

def CloseConnection(user, userID, login):
    print(user, "Authentication not passed, close the connection")
    user.send(b'Authentication not passed, close the connection')
    LoginLen = len(login)
    SpaceStr = ''
    if (LoginLen > 5):
        NumSpaces = 8 - (LoginLen - 5)
        for i in range(NumSpaces):
            SpaceStr = SpaceStr + ' '
    f = open('logs.txt', 'a')
    f.write(str(
        userID) + '\t' + login + SpaceStr + time.ctime() + '\t' + "disconnect" + '\t' + "offline" + '\t' 'Authentication not passed' + '\n')
    f.close()
    user.close()

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
            y = int(userID)*math.sin(1/rnd_value)
            #Варіант 8
            print("y = ", y)
            UserY = float(socket_user.recv(2048).decode())

            if(y == UserY):
                socket_user.send("You have been connected".encode())
                Thread_control_dict[socket_user] = False
                IsConnectSocket[socket_user] = True
                login = getUserLogin(userID)
                f = open('logs.txt', 'a')
                LoginLen = len(login)
                SpaceStr = ''
                if(LoginLen > 5):
                    NumSpaces = 8 - (LoginLen - 5)
                    for i in range(NumSpaces):
                        SpaceStr = SpaceStr + ' '
                f.write(str(userID)+'\t'+login+SpaceStr+time.ctime()+'\t'+"connect"+'\t\t'+"online"+'\n')
                f.close()
                thread_for_listen = threading.Thread(target=ping_user, args=(socket_user, userID, login, ))
                thread_for_listen.start()
                thread_for_listen2 = threading.Thread(target=getDataFrmUser, args=(socket_user,))
                thread_for_listen2.start()

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
        if(Thread_control_dict[user] == False):
            try:
                data = user.recv(2048)
                data = data.decode()
                print(data)
            except:
                pass
        else:
            return

def ping_user(user, userID, login):
    while True:
        if(IsConnectSocket[user] == True):
            Thread_control_dict[user] = True
            time.sleep(3) #Період повтору процедури аутентифікації, хв
            Autentification(user, userID, login)
            Thread_control_dict[user] = False
        else:
            return

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

def Commands():
    while True:
        try:
            command = input()
            if (command == "/journal"):
                ListUser()

            elif(command == "/register"):
                Register()

            elif (command == "/del"):
                DelUser()
        except:
            pass

def CreateFile():
    f = open('logs.txt', 'w')
    f.write('ID\tLOGIN\t\tDATE\t\t\tOPERATION\tSTATUS\tREASON\n')
    f.close()

def ListUser():
    f = open('logs.txt', 'r')
    for line in f:
        print(line)
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

def Register():
    if(getSizeofUsers() < 8):
        print("Input the ID of a new user")
        ID = input()
        if(CheckID(ID) == False):
            print("Input users login")
            login = input()
            print("Input users password")
            password = input()
            print("Input users rights")
            rights = input()

            with open('user.txt', 'r') as json_file:
                try:
                    obj = json.load(json_file)
                    obj['users'].append({
                        'ID': str(ID),
                        'login': login,
                        'password': password,
                        'rights': rights
                    })
                    with open('user.txt', 'w') as outfile:
                        json.dump(obj, outfile, indent=4)

                except:
                    Dict_data['users'].append({
                        'ID': str(ID),
                        'login': login,
                        'password': password,
                        'rights': rights
                    })
                    with open('user.txt', 'w') as outfile:
                        json.dump(Dict_data, outfile, indent=4)

            print("User registered")
        else:
            print("The ID is already registered")
            Register()
    else:
        print("The number of users is max")

def DelUser():
    print("Input the ID")
    ID = input()
    with open('user.txt', 'r') as json_file:
        obj = json.load(json_file)

    for i in obj['users']:
        if(i['ID'] == ID):
            obj['users'].remove(i)
            print("true")
            break

    with open('user.txt', 'w') as outfile:
        json.dump(obj, outfile, indent=4)


if __name__ == '__main__':
    CreateFile()
    isFileExist()
    thread_for_listen = threading.Thread(target=Commands)
    thread_for_listen.start()
    server.bind(("127.0.0.1", 5555))
    server.listen(MaxSizeofUsers)
    run()