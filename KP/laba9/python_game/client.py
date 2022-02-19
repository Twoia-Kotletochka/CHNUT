import socket
import threading
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(("127.0.0.1", 5555))

def listen_server():
    while True:
        data = client.recv(2048)
        print(data.decode("utf-8"))

def send_data():
    thread_for_listen = threading.Thread(target=listen_server)
    thread_for_listen.start()
    print("Ласкаво просимо до гри камінь, ножиці, папір!\nВиберіть(число) ваш варіант-> 1-камінь, 2-ножиці, 3-папір")
    text = input()
    client.send(text.encode("utf-8"))

if __name__ == '__main__':
    send_data()
