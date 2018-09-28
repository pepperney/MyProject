package cn.com.learn.dataStructure.graph;

/**
 * @Author: pei.nie
 * @Date:2018/9/26
 * @Description: 无权无向图
 * @See 原理参考此博客：https://www.cnblogs.com/ysocean/p/8032659.html
 */
public class Graph {

    /**
     * 顶点类
     */
    private  class Vertex {
        public char label;
        public boolean wasVisited;
        public Vertex(char label){
            this.label = label;
            wasVisited = false;
        }
    }

    /**
     * 实现深度优先搜索的栈
     */
    private class Stack{

        private final int SIZE = 20;
        private int[] st;
        private int top;

        public Stack(){
            st = new int[SIZE];
            top = -1;
        }

        public void push(int j){
            st[++top] = j;
        }

        public int pop(){
            return st[top--];
        }

        public int peek(){
            return st[top];
        }

        public boolean isEmpty(){
            return (top == -1);
        }
    }

    /**
     * 实现广度优先搜索的队列
     */
    private class Queue{
        private final int SIZE = 20;
        private int[] queArray;
        private int front;
        private int rear;

        public Queue(){
            queArray = new int[SIZE];
            front = 0;
            rear = -1;
        }

        public void insert(int j) {
            if(rear == SIZE-1) {
                rear = -1;
            }
            queArray[++rear] = j;
        }

        public int remove() {
            int temp = queArray[front++];
            if(front == SIZE) {
                front = 0;
            }
            return temp;
        }

        public boolean isEmpty() {
            return (rear+1 == front || front+SIZE-1 == rear);
        }
    }

    /**************************************开始图的代码************************************/

    private final int MAX_VERTS = 20;//表示顶点的个数
    private Vertex vertexList[];//用来存储顶点的数组
    private int adjMat[][];//用邻接矩阵来存储 边,数组元素0表示没有边界，1表示有边界
    private int nVerts;//顶点个数
    private Stack stack;//用栈实现深度优先搜索
    private Queue queue;//用队列实现广度优先搜索

    /**
     * 构造方法
     */
    public Graph(){
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;//初始化顶点个数为0
        //初始化邻接矩阵所有元素都为0，即所有顶点都没有边
        for(int i = 0; i < MAX_VERTS; i++) {
            for(int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
        stack = new Stack();
        queue = new Queue();
    }

    //将顶点添加到数组中，是否访问标志置为wasVisited=false(未访问)
    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    //注意用邻接矩阵表示边，是对称的，两部分都要赋值
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    //打印某个顶点表示的值
    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }
    /**深度优先搜索算法:
     * 1、用peek()方法检查栈顶的顶点
     * 2、用getAdjUnvisitedVertex()方法找到当前栈顶点邻接且未被访问的顶点
     * 3、第二步方法返回值不等于-1则找到下一个未访问的邻接顶点，访问这个顶点，并入栈
     *    如果第二步方法返回值等于 -1，则没有找到，出栈
     */
    public void depthFirstSearch() {
        //从第一个顶点开始访问
        vertexList[0].wasVisited = true; //访问之后标记为true
        displayVertex(0);//打印访问的第一个顶点
        stack.push(0);//将第一个顶点放入栈中

        while(!stack.isEmpty()) {
            //找到栈当前顶点邻接且未被访问的顶点
            int v = getAdjUnvisitedVertex(stack.peek());
            if(v == -1) {   //如果当前顶点值为-1，则表示没有邻接且未被访问顶点，那么出栈顶点
                stack.pop();
            }else { //否则访问下一个邻接顶点
                vertexList[v].wasVisited = true;
                displayVertex(v);
                stack.push(v);
            }
        }

        //栈访问完毕，重置所有标记位wasVisited=false
        for(int i = 0; i < nVerts; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    //找到与某一顶点邻接且未被访问的顶点
    public int getAdjUnvisitedVertex(int v) {
        for(int i = 0; i < nVerts; i++) {
            //v顶点与i顶点相邻（邻接矩阵值为1）且未被访问 wasVisited==false
            if(adjMat[v][i] == 1 && vertexList[i].wasVisited == false) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 广度优先搜索算法：
     * 1、用remove()方法检查栈顶的顶点
     * 2、试图找到这个顶点还未访问的邻节点
     * 3、 如果没有找到，该顶点出列
     * 4、 如果找到这样的顶点，访问这个顶点，并把它放入队列中
     */
    public void breadthFirstSearch(){
        vertexList[0].wasVisited = true;
        displayVertex(0);
        queue.insert(0);
        int v2;

        while(!queue.isEmpty()) {
            int v1 = queue.remove();
            while((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].wasVisited = true;
                displayVertex(v2);
                queue.insert(v2);
            }
        }

        //搜索完毕，初始化，以便于下次搜索
        for(int i = 0; i < nVerts; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    /**
     * 测试代码
     * @param args
     */
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');

        graph.addEdge(0, 1);//AB
        graph.addEdge(1, 2);//BC
        graph.addEdge(0, 3);//AD
        graph.addEdge(3, 4);//DE

        System.out.println("深度优先搜索算法 :");
        graph.depthFirstSearch();//ABCDE

        System.out.println();
        System.out.println("----------------------");

        System.out.println("广度优先搜索算法 :");
        graph.breadthFirstSearch();//ABDCE
    }
    
    
}
