import random

from Crypto.Util import number
from Crypto.Hash import SHA
import time


def extendGCD(a, n):
    if n == 0:
        info = [a, 1, 0]
        return info

    prevInfo = extendGCD(n, a % n)
    div = a / n
    mul = div * prevInfo[2]
    info = [prevInfo[0], prevInfo[2], prevInfo[1] - mul]

    return info



def RSA(fn):
#    random_int = random.randint(851, 900)
    e = number.getPrime(17, randfunc=None)
    info = extendGCD(e, fn)
    d = 0

    while info[0] != 1:
        e = number.getPrime(17, randfunc=None)
        info = extendGCD(e, fn)
        if info[0] == 1:
#            print "e is ", e
            break
#    print "GCD(", fn, ",", e, ") = ", info[0]

    if info[0] == 1:
        inverse = info[1]
        d = inverse

    d = d % fn
    # if info[0] == 1 and info[1] < 0:
    #     inverse = n + info[1]
    #     d = inverse

    return e, d


def generateS(m, d, n):
    s = pow(m, d, n)
    return s


def verify(s, e, n, m):
    m1 = pow(s, e, n)
    if m1 == int(SHA.new(m).hexdigest(), 16):
        return True
    else:
        return False


if __name__ == "__main__":
#    random_int = random.randint(500, 600)

    p = number.getPrime(512, randfunc=None)
    q = number.getPrime(512, randfunc=None)
    n = p * q
    fn = (p - 1) * (q - 1)
    global e, d
    e, d = RSA(fn)

    signed = []

    startSign = time.clock()
    for line in open("messages.txt"):
        hash = int(SHA.new(line).hexdigest(), 16)
        s = generateS(hash, d, n)
        signed.append((line, s))
    endSign = time.clock()
    print "Time to sign:",endSign - startSign

    startVerification = time.clock()
    for line in signed:
        m, s = line
        if not verify(s, e, n, m):
            print "Verification error"
    endVerfication = time.clock()
    print "Time to verify:", endVerfication - startVerification

#    p = number.getPrime(512, randfunc=None)
#    q = number.getPrime(512, randfunc=None)
#    n = p * q
#        fn = (p - 1) * (q - 1)
    startGenerateKey = time.clock()
    for i in range(0,100):
        e, d = RSA(fn)
    endGenerateKey = time.clock()
    print "Time to Generate key:", endGenerateKey - startGenerateKey
