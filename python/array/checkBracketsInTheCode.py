from collections import namedtuple

Bracket = namedtuple("Bracket", ["char", "position"])

def areMatching(left, right):
    return (left + right) in ["()", "[]", "{}"]

def findMismatch(text):
    stack = []

    for i, next in enumerate(text):
        if next in "([{":
            stack.append(Bracket(char = next, position = i + 1))
        if next in ")]}":
            if len(stack) == 0 or areMatching(stack.pop().char, next) == 0:
                return i + 1

    if len(stack) == 0:
        return 'success'
    else:
        return stack.pop().position


def main():
    text = input();
    mismatch = findMismatch(text)
    print(mismatch);

if __name__ == "__main__":
    main()