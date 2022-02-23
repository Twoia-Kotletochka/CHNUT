#include <mpi.h>
#include <iostream>
#include <cstring>
#include <string>


int main(int argc, char** argv) {


#if 1
		MPI_Init(NULL, NULL);

		// Отримайти кількість процесів 
		int world_size;
		MPI_Comm_size(MPI_COMM_WORLD, &world_size);

		// Отримайтb ранг процесу
		int world_rank;
		MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

		char processor_name[MPI_MAX_PROCESSOR_NAME];
		int name_len;
		MPI_Get_processor_name(processor_name, &name_len);

		fprintf(stdout, "Process %d of %d\n", world_rank, world_size);

		MPI_Finalize();
#endif

#if 0
		char message[] = "Hi, Second Processor!";
		char inmsg[30];
		int myrank; 
		MPI_Init(&argc, &argv);
		MPI_Comm_rank(MPI_COMM_WORLD, &myrank);

		int world_size;
		MPI_Comm_size(MPI_COMM_WORLD, &world_size);

		if (myrank == 0) {
			MPI_Send(message, strlen(message)+1, MPI_CHAR, 1, 1, MPI_COMM_WORLD);
		}
		if (myrank == 1){
			MPI_Recv(inmsg, 30, MPI_CHAR, 0, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
			printf("received: %s\n", inmsg);
		}

		MPI_Finalize();
#endif

#if 0
		int myrank, size, message; int TAG = 0; MPI_Status status;
		MPI_Init(&argc, &argv);
		MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
		MPI_Comm_size(MPI_COMM_WORLD, &size); 
		message = myrank;
		if ((myrank % 2) == 0) {
			if ((myrank + 1) != size)
				MPI_Send(&message, 1, MPI_INT, myrank + 1, 0, MPI_COMM_WORLD);
		}
		else {
			if (myrank != 0)     
				MPI_Recv(&message, 1, MPI_INT, myrank - 1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
			printf("The %i rank received :%i\n", myrank, message);
		}
		MPI_Finalize();
#endif

	return 0;
}