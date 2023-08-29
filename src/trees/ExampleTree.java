package trees;

import java.util.Stack;

public class ExampleTree {
    public static void main(String[] args) {
        Tree tree = new Tree();

//        tree.insertNode(7);
//        tree.printTree();
//        tree.insertNode(7);
//        tree.insertNode(10);
//        tree.insertNode(2);
//        tree.insertNode(4);
//        tree.insertNode(3);
//        tree.insertNode(5);
//        tree.insertNode(1);
//        tree.insertNode(6);
//        tree.insertNode(11);
//        tree.printTree();
//
//        tree.deleteNode(2);

        tree.insertNode(20);
        tree.insertNode(10);
        tree.insertNode(15);
        tree.insertNode(13);
        tree.insertNode(14);
        tree.insertNode(16);
        tree.insertNode(9);
        tree.insertNode(25);
        tree.insertNode(22);
        tree.insertNode(26);

        tree.printTree();
        tree.deleteNode(10);
        tree.printTree();
//        tree.deleteNode(10);
//        tree.printTree();
    }
}


class Tree {
    private Node rootNode; // корневой узел

    public Tree() { // Пустое дерево
        rootNode = null;
    }

    public Node findNodeByValue(int value) { // поиск узла по значению
        Node currentNode = rootNode; // начинаем поиск с корневого узла

        while (currentNode.getValue() != value) { // поиск покуда не будет найден элемент или не будут перебраны все
            if (value < currentNode.getValue()) { // движение влево?
                currentNode = currentNode.getLeftChild();
            } else { //движение вправо
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) { // если потомка нет,
                return null; // возвращаем null
            }
        }

        return currentNode; // возвращаем найденный элемент
    }

    public void insertNode(int value) { // метод вставки нового элемента
        Node newNode = new Node(); // создание нового узла
        newNode.setValue(value);
        // вставка данных
        if (rootNode == null) { // если корневой узел не существует
            rootNode = newNode;// то новый элемент и есть корневой узел
        } else { // корневой узел занят
            Node currentNode = rootNode; // начинаем с корневого узла
            Node parentNode;

            while (true) {   // мы имеем внутренний выход из цикла
                parentNode = currentNode;
                if (value == currentNode.getValue()) {   // если такой элемент в дереве уже есть, не сохраняем его
                    return;                             // просто выходим из метода
                } else if (value < currentNode.getValue()) {   // движение влево?
                    if (currentNode.getLeftChild() == null) {   // если был достигнут конец цепочки,
                        currentNode.setLeftChild(newNode); //  то вставить слева и выйти из методы
                        return;
                    }
                    currentNode = currentNode.getLeftChild();
                } else { // Или направо?
                    if (currentNode.getRightChild() == null) {   // если был достигнут конец цепочки,
                        parentNode.setRightChild(newNode);  //то вставить справа
                        return; // и выйти
                    }
                    currentNode = currentNode.getRightChild();
                }
            }
        }
    }

    public boolean deleteNode(int value) {   // Удаление узла с заданным ключом

        Node currentNode = rootNode;
        Node parentNode = rootNode;
        boolean isLeftChild = true;         //местоположение относительно родительского элемента

        while (currentNode.getValue() != value) { // начинаем поиск узла
            parentNode = currentNode;

            if (value < currentNode.getValue()) { // Определяем, нужно ли движение влево?
                isLeftChild = true;
                currentNode = currentNode.getLeftChild();
            }
            else { // или движение вправо?
                isLeftChild = false;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null)
                return false; // yзел не найден
        }

        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) { // узел просто удаляется, если не имеет потомков

            if (currentNode == rootNode) // если узел - корень, то дерево очищается
                rootNode = null;
            else if (isLeftChild)
                parentNode.setLeftChild(null); // если нет - узел отсоединяется, от родителя
            else
                parentNode.setRightChild(null);
        } else if (currentNode.getRightChild() == null) { // узел заменяется левым поддеревом, если правого потомка нет

            if (currentNode == rootNode)                    //хотим удалить root не равен null только левый предок
                rootNode = currentNode.getLeftChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getLeftChild());
            else
                parentNode.setRightChild(currentNode.getLeftChild());
        } else if (currentNode.getLeftChild() == null) { // узел заменяется правым поддеревом, если левого потомка нет

            if (currentNode == rootNode)
                rootNode = currentNode.getRightChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getRightChild());
            else
                parentNode.setRightChild(currentNode.getRightChild());
        } else { // если есть два потомка, узел заменяется преемником
            Node heir = receiveHeir(currentNode);// поиск преемника для удаляемого узла

            if (currentNode == rootNode)
                rootNode = heir;
            else if (isLeftChild)
                parentNode.setLeftChild(heir);
            else
                parentNode.setRightChild(heir);
        }
        return true; // элемент успешно удалён
    }

    private Node receiveHeir(Node node) {
        Node parentNode = node;
        Node currentNode = node.getRightChild(); // Переход к правому потомку

        while (currentNode.getLeftChild() != null) {    // Пока остаются левые потомки
            parentNode = currentNode;      // потомка задаём как текущий узел
            currentNode = currentNode.getLeftChild();   // переход к левому потомку
        }
        // Если преемник не является
        if (currentNode == node.getRightChild()) { // правым потомком, создать связи между узлами
            currentNode.setLeftChild(node.getLeftChild());
        } else {
            parentNode.setLeftChild(currentNode.getRightChild());
            currentNode.setRightChild(node.getRightChild());
            currentNode.setLeftChild(node.getLeftChild());
        }
        return currentNode;// возвращаем приемника
    }

    public void printTree() { // метод для вывода дерева в консоль
        Stack<Node> globalStack = new Stack<>(); // общий стек для значений дерева
        globalStack.push(rootNode);
        int gaps = 32; // начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева

        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<>(); // локальный стек для задания потомков элемента
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (!globalStack.isEmpty()) { // покуда в общем стеке есть элементы
                Node temp = (Node) globalStack.pop(); // берем следующий, при этом удаляя его из стека
                if (temp != null) {
                    System.out.print(temp.getValue()); // выводим его значение в консоли
                    localStack.push(temp.getLeftChild()); // соохраняем в локальный стек, наследники текущего элемента
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");// - если элемент пустой
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;// при переходе на следующий уровень расстояние между элементами каждый раз уменьшается
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }
}


class Node {
    private int value; // ключ узла
    private Node leftChild; // Левый узел потомок
    private Node rightChild; // Правый узел потомок

    public void printNode() { // Вывод значения узла в консоль
        System.out.println(" Выбранный узел имеет значение :" + value);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(final Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(final Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
