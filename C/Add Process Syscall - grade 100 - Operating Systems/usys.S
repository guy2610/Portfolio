//name: Guy Even, id:318911963
#include "syscall.h"
#include "traps.h"

// All "standard" SYSCALLS are added by this:
#define SYSCALL(name) \
  .globl name; \
  name: \
    movl $SYS_ ## name, %eax; \
    int $T_SYSCALL; \
    ret

// !!!! Adding "new" "SYSCALLfork(fork)" instead of SYSCALL(fork) 
//  without changing syscall.h !!! 1 is SYSCALL number, look in syscall.c
#define SYSCALLFORK(name) \
  .globl name; \
  name: \
    movl $1, %eax; \
    int $T_SYSCALL; \
    ret

// You need to add your new SYSCALL by the same way(here is a good place)
//macro for the cps function
#define SYSCALLCPS163(name) \
  .globl name; \
  name: \
    movl $163, %eax; \
    int $T_SYSCALL; \
    ret


SYSCALLFORK(fork) //instead of SYSCALL(fork) !!!
//SYSCALL(fork)
SYSCALL(exit)
SYSCALL(wait)
SYSCALL(pipe)
SYSCALL(read)
SYSCALL(write)
SYSCALL(close)
SYSCALL(kill)
SYSCALL(exec)
SYSCALL(open)
SYSCALL(mknod)
SYSCALL(unlink)
SYSCALL(fstat)
SYSCALL(link)
SYSCALL(mkdir)
SYSCALL(chdir)
SYSCALL(dup)
SYSCALL(getpid)
SYSCALL(sbrk)
SYSCALL(sleep)
SYSCALL(uptime)
SYSCALLCPS163(cps163)//changed, calling the macro for cps like the macro for fork
