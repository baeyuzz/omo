sample_str = """hi. i am yujin. good to see you. high-level language."""


def toUpperCase(str):
    res = ''
    arr = str.split('. ')
    for s in arr:
        s = s.strip()
        s += '. '
        s = s.capitalize()
        res += s
    return res[:-2]


def dashToUpper(str):
    res = ''
    arr = str.split(' ')
    for s in arr:
        if "-" in s:
            s = s.upper()
        res += s + ' '
    return res


print(dashToUpper(toUpperCase(sample_str)))
