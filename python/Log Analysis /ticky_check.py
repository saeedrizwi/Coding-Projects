import sys
import re
import csv
import operator

errors = {}
per_user = {}

with open("syslog.log") as file:
    for line in file.readlines():
        match = re.search(r"ticky: ([\w+]*):? ([\w' ]*)[\[[#0-9]*\]?]? ?\((.*)\)$", line)

        code, error_msg, user = match.group(1), match.group(2), match.group(3)

        if code == "ERROR":
            errors[error_msg] = errors.get(error_msg, 0) + 1

        if user not in per_user:
            per_user[user] = {"INFO": 0, "ERROR": 0}

        per_user[user][code] += 1

errors_list = sorted(errors.items(), key=operator.itemgetter(1), reverse=True)
user_list = sorted(per_user.items())

with open('user_statistics.csv', 'w', newline='') as user_csv:
    writer = csv.writer(user_csv)
    writer.writerow(['Username', 'INFO', 'ERROR'])
    for user, counts in user_list:
        writer.writerow([user, counts['INFO'], counts['ERROR']])

with open('error_msg.csv', 'w', newline='') as error_csv:
    writer = csv.writer(error_csv)
    writer.writerow(['Error', 'Count'])
    writer.writerows(errors_list)