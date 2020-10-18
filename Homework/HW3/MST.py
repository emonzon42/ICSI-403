import sys
import numpy as np

def main():
    if(len(sys.argv) < 2):
        print('Please start the program like this: "MST.py inputfilepath options"')
    'Homework/HW3/infile.txt'
    graph = load(sys.argv[1])
    print(graph)

    if(len(sys.argv) > 2):
        print(sys.argv)
        #todo: step 7 when args[1] = '-e' print list of edges and costs
    

    pass


def kruskal(G,w):



    pass

def load(fn):
    '''
    Returns a tuple matrix in the format of: [[u,v],edgecost] for each edge in input file (each edge is a new row)
    where [u,v] are two nodes representing a edge and edgecost is their cost
    '''
    data = np.zeros((10000,2))
    with open(fn, "rt") as file:
        i = 0
        edge = []
        weight = []
        for line in file:
            if len(line.split(' ')) < 2:
                continue
            print(line.split(' '))
            print(line.split(' ')[2].split('\n')[0])
            edge.append([line.split(' ')[0],line.split(' ')[1]])
            weight.append(line.split(' ')[2].split('\n')[0])
            i+1
        data[:,0] = edge
        data[:,1] = weight
        #print (line.split(';;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;')[0])
    file.close()
    return data

if __name__ == "__main__":
    main()