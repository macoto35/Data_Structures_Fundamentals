class Node:
    def __init__(self, key, leftIdx, rightIdx):
        self.key = key
        self.left = None
        self.right = None
        self.leftIdx = leftIdx
        self.rightIdx = rightIdx

def getBinaryTree(arr):
    for node in arr:
        if node.leftIdx > -1:
            node.left = arr[node.leftIdx]
        if node.rightIdx > -1:
            node.right = arr[node.rightIdx]

def inOrder(node, result):
    if node is None:
        return None

    inOrder(node.left, result)
    result.append(node.key)
    inOrder(node.right, result)

    return result

def preOrder(node, result):
    if node is None:
        return None

    result.append(node.key)
    preOrder(node.left, result)
    preOrder(node.right, result)

    return result
    

def postOrder(node, result):
    if node is None:
        return None

    postOrder(node.left, result)
    postOrder(node.right, result)
    result.append(node.key)

    return result

# single check
# input
n = int(input())
arr = []
exec('k, l, r = map(int, input().split()); arr.append(Node(k, l, r));' * n)

# create binary tree
getBinaryTree(arr)

# print
print(*inOrder(arr[0], []))
print(*preOrder(arr[0], []))
print(*postOrder(arr[0], []))


'''# random check
n = int(input())
arr = []
seq = 0
for i in range((2**n) - 1):
    l = -1 if i + 1 >= 2**(n-1) else seq + 1
    r = -1 if i + 1 >= 2**(n-1) else seq + 2
    arr.append(Node(i, l, r))
    #print(i, l, r)
    seq += 2

getBinaryTree(arr)
print(*inOrder(arr[0], []))
print(*preOrder(arr[0], []))
print(*postOrder(arr[0], []))'''
