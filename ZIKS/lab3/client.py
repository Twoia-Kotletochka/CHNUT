import socket
import threading
import math

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

def listen_system():
    while True:
        try:
            data = client.recv(2048)
            if(data.decode("utf-8") != "ping"):
                print(data.decode("utf-8"))
        except:
            pass

def ConnectSystem():
    print("Input ID")
    MyID = input()
    client.send(MyID.encode("utf-8"))
    rnd_x = client.recv(2048).decode()
    try:
        rnd_x = int(rnd_x)
        print("x = ", rnd_x)
        intId = int(MyID)

        #Варіант 8
        result = intId*math.sin(1/rnd_x)
        #Варіант 8
        
        print("result = ", result)
        client.send(str(result).encode())
        thread_for_listen = threading.Thread(target=listen_system)
        thread_for_listen.start()

    except ValueError:
        print(rnd_x)
        client.close()

if __name__ == '__main__':
    client.connect(("127.0.0.1", 5555))
    ConnectSystem()


