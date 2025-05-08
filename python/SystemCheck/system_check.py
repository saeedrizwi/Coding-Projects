import shutil
import psutil


def check_disk_usage(disk):
    disk_use=shutil.disk_usage(disk)
    free = disk_use.free / disk_use.total * 100
    return free

def check_cpu_usage():
    cpu_use = psutil.cpu_percent(0.5)
    return cpu_use

print("Disk free space percentage : {:.2f}%\nCPU usage in 0.5 seconds : {}".format(check_disk_usage("/"),check_cpu_usage()))