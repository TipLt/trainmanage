/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hai
 */
import java.io.*;
import java.util.*;

public class TrainBST {

    private TrainNode root;

    public TrainBST() {
        root = null;
    }

    public void insert(Train train) {
        root = insertRec(root, train);
    }

    private TrainNode insertRec(TrainNode node, Train train) {
        if (node == null) {
            return new TrainNode(train);
        }
        if (train.tcode.compareTo(node.data.tcode) < 0) {
            node.left = insertRec(node.left, train);
        } else if (train.tcode.compareTo(node.data.tcode) > 0) {
            node.right = insertRec(node.right, train);
        } else {
            System.out.println("Train with code " + train.tcode + " already exists.");
        }
        return node;
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(TrainNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.data);
            inorderRec(node.right);
        }
    }

    public void breadthFirst() {
        if (root == null) {
            return;
        }
        Queue<TrainNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TrainNode current = queue.remove();
            System.out.println(current.data);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    public void inorderToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            inorderToFileRec(root, bw);
            System.out.println("In-order output written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void inorderToFileRec(TrainNode node, BufferedWriter bw) throws IOException {
        if (node != null) {
            inorderToFileRec(node.left, bw);
            bw.write(node.data.toString());
            bw.newLine();
            inorderToFileRec(node.right, bw);
        }
    }

    public Train search(String tcode) {
        return searchRec(root, tcode);
    }

    private Train searchRec(TrainNode node, String tcode) {
        if (node == null) {
            return null;
        }
        if (tcode.equals(node.data.tcode)) {
            return node.data;
        }
        if (tcode.compareTo(node.data.tcode) < 0) {
            return searchRec(node.left, tcode);
        } else {
            return searchRec(node.right, tcode);
        }
    }

    public void delete(String tcode) {
        root = deleteRec(root, tcode);
    }

    private TrainNode deleteRec(TrainNode node, String tcode) {
        if (node == null) {
            System.out.println("Train with code " + tcode + " not found.");
            return node;
        }
        if (tcode.compareTo(node.data.tcode) < 0) {
            node.left = deleteRec(node.left, tcode);
        } else if (tcode.compareTo(node.data.tcode) > 0) {
            node.right = deleteRec(node.right, tcode);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            TrainNode pred = node.left;
            while (pred.right != null) {
                pred = pred.right;
            }
            node.data = pred.data;
            node.left = deleteRec(node.left, pred.data.tcode);
        }
        return node;
    }

    public int count() {
        return countRec(root);
    }

    private int countRec(TrainNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countRec(node.left) + countRec(node.right);
    }

    public void balance() {
        List<Train> list = new ArrayList<>();
        storeInorder(root, list);
        root = buildBalancedBST(list, 0, list.size() - 1);
        System.out.println("BST has been balanced.");
    }

    private void storeInorder(TrainNode node, List<Train> list) {
        if (node != null) {
            storeInorder(node.left, list);
            list.add(node.data);
            storeInorder(node.right, list);
        }
    }

    private TrainNode buildBalancedBST(List<Train> list, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TrainNode node = new TrainNode(list.get(mid));
        node.left = buildBalancedBST(list, start, mid - 1);
        node.right = buildBalancedBST(list, mid + 1, end);
        return node;
    }

    public void loadData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 6) {
                    continue;
                }
                String tcode = parts[0].trim();
                String trainName = parts[1].trim();
                int seat = Integer.parseInt(parts[2].trim());
                int booked = Integer.parseInt(parts[3].trim());
                double departTime = Double.parseDouble(parts[4].trim());
                String departPlace = parts[5].trim();
                Train train = new Train(tcode, trainName, seat, booked, departTime, departPlace);
                insert(train);
            }
            System.out.println("Train data loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading train data from file: " + e.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("Error parsing number: " + nfe.getMessage());
        }
    }
}
