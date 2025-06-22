//name: Guy Even, id:318911963
#include "syscall.h"
#include "traps.h"

#define SYSCALL(name) \
  .globl name; \
  name: \
    movl $SYS_ ## name, %eax; \
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

SYSCALL(fork)
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
SYSCALL(mount)
SYSCALL(umount)
SYSCALL(printmounts)
SYSCALL(printdevices)
SYSCALL(unshare)
SYSCALL(usleep)
SYSCALL(ioctl)
SYSCALL(getppid)
SYSCALL(getcpu)
SYSCALL(getmem)
SYSCALL(kmemtest)
SYSCALLCPS163(cps163)//changed, calling the macro for cps like the macro for fork
