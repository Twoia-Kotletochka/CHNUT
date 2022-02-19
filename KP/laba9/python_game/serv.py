import socket
import threading

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(("127.0.0.1", 5555))
server.listen(5)

users = []
d = {}

def send_(data, user_self):
    p1 = 0
    p2 = 0
    p3 = 0
    if len(users) == 3:
        if len(d) == 3:
            p1 = int(d[users[0]])
            p2 = int(d[users[1]])
            p3 = int(d[users[2]])
            game(p1,p2,p3,users)


def listen_user(user):
    try:
        data = user.recv(2048)
        text = data.decode("utf-8")
        d[user] = text
        send_(data, user)

    except:
        user.close()
        for u in users:
            if u == user:
                users.remove(u)


def start():
    while True:
        socket_user, adress_user = server.accept()
        users.append(socket_user)
        thread_for_user = threading.Thread(target=listen_user, args= (socket_user,))
        thread_for_user.start()


def game(p1, p2, p3, users):
    a = 0
    b = 0
    c = 0
    if p1 == p2 and p1 == p3 and p2 == p3:
        users[0].send("НІЧИЯ!".encode("utf-8"))
        users[1].send("НІЧИЯ!".encode("utf-8"))
        users[2].send("НІЧИЯ!".encode("utf-8"))
    else:
        if (p1 + p2 + p3) == 6:
            users[0].send("КАША".encode("utf-8"))
            users[1].send("КАША".encode("utf-8"))
            users[2].send("КАША".encode("utf-8"))
        else:
            if p1 == 1 and p2 == 2:
                a = 1
            if p1 == 1 and p2 == 3:
                b = 1
            if p1 == 3 and p2 == 1:
                a = 1
            if p1 == 3 and p2 == 2:
                b = 1
            if p1 == 2 and p2 == 1:
                b = 1
            if p1 == 2 and p2 == 3:
                b = 1

            if p1 == 1 and p3 == 2:
                a = 1
            if p1 == 1 and p3 == 3:
                c = 1
            if p1 == 3 and p3 == 1:
                a = 1
            if p1 == 3 and p3 == 2:
                c = 1
            if p1 == 2 and p3 == 1:
                c = 1
            if p1 == 2 and p3 == 3:
                a = 1

            if p3 == 1 and p2 == 2:
                c = 1
            if p3 == 1 and p2 == 3:
                b = 1
            if p3 == 3 and p2 == 1:
                c = 1
            if p3 == 3 and p2 == 2:
                b = 1
            if p3 == 2 and p2 == 1:
                b = 1
            if p3 == 2 and p2 == 3:
                с = 1
            if a >= 1:
                users[0].send("Ви перемогли!".encode("utf-8"))
            else:
                users[0].send("Ви програли ;(".encode("utf-8"))

            if b >= 1:
                users[1].send("Ви перемогли!".encode("utf-8"))
            else:
                users[1].send("Ви програли ;(".encode("utf-8"))

            if c >= 1:
                users[2].send("Ви перемогли!".encode("utf-8"))
            else:
                users[2].send("Ви програли ;(".encode("utf-8"))


if __name__ == '__main__':
    start()
