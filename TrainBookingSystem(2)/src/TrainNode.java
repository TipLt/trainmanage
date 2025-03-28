/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hai
 */
public class TrainNode {
    Train data;
    TrainNode left, right;
    
    public TrainNode(Train data) {
        this.data = data;
        this.left = this.right = null;
    }
}
