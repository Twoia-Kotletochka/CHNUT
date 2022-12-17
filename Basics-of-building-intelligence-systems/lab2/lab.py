import math
import random
import matplotlib.pyplot as plt

POPULATION_SIZE = 34
ONE_INDIVIDUAL_SIZE = 34
MAX_GENERATION = 10
pt_crossover = 0.75
pm_mutation = 0.001

fit_value = []
BestNumber = 0


def getMiddleParents(values):
    sum = 0
    for value in values:
        sum += value
    sum = sum / POPULATION_SIZE
    return sum


def fitness_score():
    global populations
    global fit_value, medium_values
    fit_value = []
    for i in range(POPULATION_SIZE):
        chromosome_value = 0
        for j in range(ONE_INDIVIDUAL_SIZE - 1, -1, -1):
            chromosome_value += populations[i][j] * (2 ** (7 - j))
        print(f'значення хромосоми в 10 системі = {chromosome_value}')
        func_result = - \
            (math.pow((3*math.pow(chromosome_value, 2) - 12), 2) / 256)
        print(f'func res = {func_result}')
        fit_value.append(func_result)

    # сортування рости
    fit_value, populations = zip(
        *sorted(zip(fit_value, populations), reverse=True))
    populations = list(populations)
    fit_value = list(fit_value)

    # знайти середнє значення чисельності населення
    sum_fit = getMiddleParents(fit_value)
    medium_values.append(sum_fit)
    BestNumber = fit_value[0]

    print(f'fit_value {fit_value}')
    print(f'population {populations}')
    print(f'best value {BestNumber}')


def selectparent():
    global parents, fit_value, populations
    parents = []
    total_sum = sum(fit_value)
    print(f'sum {total_sum}')
    if total_sum == 0:
        return 0
    # знайти нормалізовану форму та створити список
    normalized_fitness_form = [i / total_sum for i in fit_value]

    # знайти сукупні значення придатності для вибору колеса рулетки
    cumulative_fitness_value = []
    start = 0
    for norm_value in normalized_fitness_form:
        start = start + norm_value
        cumulative_fitness_value.append(start)

    print(f'normalized {normalized_fitness_form}')
    print(f'cumulative {cumulative_fitness_value}')

    # Формуємо нову популяцію батьків для схрещування, розмір рівний розміру популяції
    # Батьки можуть повторятися
    for count in range(POPULATION_SIZE):
        # Крутимо рулетку, отримуємо число
        rand_numb = random.uniform(0, 1)
        individual_number = 0  # Номер батька
        # Перевіряємо співпадання числа з батьком
        for number_parent, score in enumerate(cumulative_fitness_value):
            if rand_numb <= score:
                # Додаємо батька у сформований список
                parents.append(populations[-(number_parent + 1)])
                break

        individual_number += 1

    print(f'parents len {len(parents)}')


def crossover():
    global parents
    #
    pairs_count = int(len(parents) / 2)
    for pair in range(pairs_count):
        chanse_cross = random.randint(0, 100)
        if (chanse_cross <= 61):
            # Вибираємо точку для одноточкового обміну
            cross_point = random.randint(0, ONE_INDIVIDUAL_SIZE - 1)
            rand_1 = random.randint(0, 7)  # Перший батько
            rand_2 = random.randint(0, 7)  # Другий батько
            while rand_1 == rand_2:  #
                rand_2 = random.randint(0, 7)

            # Формуємо частини для обміну
            fst_parent_bit = parents[rand_1][cross_point:]
            sec_parent_bit = parents[rand_2][cross_point:]

            print(f'par 1 {parents[rand_1]} par2 {parents[rand_2]}')

            # Створюємо нових батьків
            new_parent1 = parents[rand_1][:cross_point] + sec_parent_bit
            new_parent2 = parents[rand_2][:cross_point] + fst_parent_bit

            # Видаляємо старих батьків, ті що не розмножилися залишаються в списку
            if (rand_2 > rand_1):
                parents.remove(parents[rand_2])
                parents.remove(parents[rand_1])
            else:
                parents.remove(parents[rand_1])
                parents.remove(parents[rand_2])

            # Додаємо нових
            parents.append(new_parent1)
            parents.append(new_parent2)

            print(f'updated parents {parents}')


def mutation():
    global populations, parents
    mute = random.randint(0, 100)
    if mute <= 10:
        x = random.randint(0, POPULATION_SIZE - 1)
        y = random.randint(0, ONE_INDIVIDUAL_SIZE - 1)

        if parents[x][y] == 1:
            parents[x][y] = 0
            print(f'Muted bit {parents[x][y]} in parent{parents[x]}')

        if parents[x][y] == 0:
            parents[x][y] = 1
            print(f'Muted bit {parents[x][y]} in parent{parents[x]}')

    populations = parents


if __name__ == "__main__":
    # ініціалізувати населення
    populations = [[random.randint(0, 1) for x in range(
        ONE_INDIVIDUAL_SIZE)] for i in range(POPULATION_SIZE)]
    parents = []
    medium_values = []
    for i in range(MAX_GENERATION):
        fitness_score()
        if (selectparent()) == 1:
            break
        crossover()
        mutation()

    print(medium_values)
    print(f'Найкраще число {BestNumber}')

    plt.plot(medium_values, color="blue")
    plt.xlabel('Покоління')
    plt.ylabel('Середня пристосованість')
    plt.title('Середня пристосованість в залежності від покоління')
    plt.show()
