import socket
import threading
import math
import sys
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

def listen_system():
    count = 0
    while True:
        try:
            data = client.recv(2048)
            if(data.decode("utf-8") == "ping"):
                pass
            if (data.decode("utf-8") == "auth"):
                if(count == 0):
                    print("You need to pass authentication")
                count =+ 1
                func = client.recv(2048).decode()
                print("Count a value", func)
                result = input()
                client.send(result.encode())
                server_answer = client.recv(2048).decode()
                if(server_answer == 'Authentication not passed, close the connection'):
                    print(server_answer)
                    client.close()
                    sys.exit()
                print(server_answer)
                if(server_answer == '4 Authentication passed'):
                    print('You passed all tests!')
                    count = 0
            else:
                print(data.decode("utf-8"))
        except:
            client.close()

def ConnectSystem():
    print("Input your ID")
    MyID = input()
    client.send(MyID.encode("utf-8"))
    rnd_x = client.recv(2048).decode()
    try:
        rnd_x = int(rnd_x)
        print("x = ", rnd_x)
        intId = int(MyID)

        #Варіант 8
        y = intId*math.sin(1/rnd_x)
        #Варіант 8

        print("y = ", y)
        client.send(str(y).encode())
        thread_for_listen = threading.Thread(target=listen_system)
        thread_for_listen.start()

    except ValueError:
        print(rnd_x)
        client.close()

if __name__ == '__main__':
    client.connect(("127.0.0.1", 5555))
    ConnectSystem()