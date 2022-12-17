from collections import deque
from graphs import Adjacency_Matrix
import threading
import time


def bfs(graph_to_search, start, end):
    queue = [[start]]  # Створюємо чергу вершин
    print(queue)
    visited = set()  # Створюємо список пройдених вершин на базі сету

    while queue:
        # Отримуємо перший елемент черги, список
        path = queue.pop(0)
        print(path)

        # Отримуємо останній елемент списку
        vertex = path[-1]
        print(vertex)

        # Перевіряємо і повертаємо шлях у разі успіху
        if vertex == end:
            return path
        # Перевіряємо чи не пройдений вже елемент
        elif vertex not in visited:
            # отримуємо нові вершини і додаємо до черги/ отримуємо по ключу
            for current_neighbour in graph_to_search.get(vertex, []):
                print(current_neighbour)
                new_path = list(path)
                print(new_path)
                new_path.append(current_neighbour)
                queue.append(new_path)
                print(f'queue {queue}')

            # Додаємо до відвіданого
            visited.add(vertex)

# Recursive search direct in depth


def dfs_matrix(matrix: list, startRow, endPoint, visited: set, find: bool, way: set):
    if find == False:
        #  Проходимо вершини графаs
        for node_index, node_list in enumerate(matrix):
            # Перевіяємо чи не знайдено вершину
            if startRow == endPoint:
                print(f'Знайдено точку пошуку {startRow}')
                way.add(endPoint)
                # print(visited)
                print(f'Шлях {way}')
                find = True
                exit()

            # Перевірка досягнення стартової вершини
            if (node_index + 1) == startRow:
                visited.add(node_index + 1)  # Помічаємо вершину як пройдену
                way.add(node_index + 1)

                edge_number = 0
                ind_list = list()

                count_end = 0
                count_edge = 0
                # Перевіряємо індекси і їх значення в кожній вершині
                for index, edge in enumerate(node_list):
                    if edge == 1:
                        edge_number = edge_number + 1
                        ind_list.append(index + 1)
                        print(
                            f'Вузол {node_index + 1} з’єднаний з {index + 1}')
                        if not (index + 1) in visited:
                            count_edge += 1
                            # Запускаємо рекурсію
                            # Повертає 1 у разі кінечної вершини
                            if (dfs_matrix(matrix, index + 1, endPoint, visited, find, way)) == 1:
                                count_end += 1  # Сумуємо кількість кінечних вершин в проміжних вершинах

                if (count_end == count_edge):  # Якщо кількість не пройдених вершин рівна кінечним вершинам
                    # Вузол теж кінечний, видаляємо зі шляху
                    way.remove(node_index + 1)
                    return 1

                # if edge_number == 1 and (node_index + 1) != endPoint:
                #     way.remove(node_index + 1)
                #     return 1


def search_bfs(start, graph, search_queue, visited, end, middle_list: list):
    search_queue += graph[start]
    while search_queue:
        # print(f'Search queue {search_queue}')
        node = search_queue.popleft()
        # print(f'Get node {node}')
        k = 0
        if not node in visited:
            # Перевірямо середні вершини, у разі пройдення зупиняємо
            for i in middle_list:
                if node == i:
                    k += 1
                    print(f'{threading.current_thread()} ,Middle tree {node}')
                    if k == 2:
                        return

            else:
                search_queue += graph[node]
                visited += [node]


# Двонаправлений пошук
def bidirectional_search_bfs_based(startPoint, endPoint, graph, middle_list: list):
    # Створюємо черги
    searchQueue_direct = deque()
    searchQueue_backward = deque()
    visited_dir = []
    visited_back = []

    # Створюємо потоки для кожного направлення
    thread_dir = threading.Thread(target=search_bfs,
                                  args=(startPoint, graph, searchQueue_direct, visited_dir, endPoint, middle_list,))
    thread_back = threading.Thread(target=search_bfs,
                                   args=(endPoint, graph, searchQueue_backward, visited_back, startPoint, middle_list,))
    # Запускаємо потоки
    thread_dir.start()
    thread_back.start()

    # Перевіряємо списки на середні вершини
    while True:
        time.sleep(1)
        for i in middle_list:
            if i in visited_dir and i in visited_back:
                print("Done")
                return


if (__name__ == "__main__"):
    dfs_matrix(Adjacency_Matrix, 1, 13, set(), False, set())
