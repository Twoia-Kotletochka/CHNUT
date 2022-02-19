// Includes:
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
#include <time.h>
#include <setjmp.h>

#ifdef __x86_64__
typedef unsigned long address_t;
#else
typedef unsigned int address_t;
#endif

// Thread Status structure
typedef struct status_t
{
    int id;
    enum { RUNNING, READY, SLEEPING, SUSPENDED, FINISHED } state;
    unsigned no_of_bursts;
    unsigned total_exec_time;
    unsigned total_sleep_time;
    unsigned total_wait_time;
    unsigned avg_exec_time;
    unsigned avg_wait_time;
    unsigned wake_time;
} status_t;

// Thread Structure
typedef struct thread_t
{
    char* stack;
    status_t* status;
    int priority;
    address_t pc;
    address_t sp;
} thread_t;

// Thread node
typedef struct thread_node_t
{
    thread_t* thread;
    struct thread_node_t* next;
} thread_node_t;

// Queue of threads
typedef struct thread_queue_t
{
    thread_node_t* head;
    thread_node_t* tail;
    int size;
} thread_queue_t;

address_t translate_address(address_t addr);

thread_t* thread_dequeue(thread_queue_t* q);
thread_t* GetNextThread();

void thread_enqueue(thread_t* t, thread_queue_t* q);
void setup();
void print_list(thread_queue_t* q);
void YieldCPU();
void Dispatch(int sig);
void Go();
void CleanUp();
void start_timer();
void SleepThread(int sec);
int CreateThread(void (*f) (void), int priority);
int RemoveFromList(int thread_id, thread_queue_t* q);
int GetMyId();
int DeleteThread(int thread_id);
int SuspendThread(int thread_id);
int ResumeThread(int thread_id);
int GetStatus(int thread_id, status_t* status);
unsigned GetCurrentTime();