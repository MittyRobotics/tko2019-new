package com.amhsrobotics.autonomous.graph;

import com.amhsrobotics.autonomous.constants.AutoPaths;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;

public class SequenceTree extends JTree {

    TreePath currentPath;

    public SequenceTree(DefaultMutableTreeNode root){
        super(root);



        setPreferredSize(new Dimension(250,200));

        TreeCellRenderer renderer = getCellRenderer();

        DefaultTreeCellRenderer dtcr =
                (DefaultTreeCellRenderer)renderer;
        // Set the various colors
        dtcr.setBackgroundNonSelectionColor((new Color(80, 80, 80)));
        dtcr.setBackgroundSelectionColor(Color.gray);
        dtcr.setTextSelectionColor(Color.white);
        dtcr.setTextNonSelectionColor(new Color(90,199,217));

        ListNode node1 = new ListNode("RED Front Left Hatch Auton");
        ListNode node2 = new ListNode("RED Front Right Hatch Auton");
        ListNode node3 = new ListNode("BLUE Front Left Hatch Auton");
        ListNode node4 = new ListNode("BLUE Front Right Hatch Auton");
        getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                currentPath = e.getPath();
        }});

        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = getRowForLocation(e.getX(), e.getY());
                TreePath selPath = getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                    if(e.getClickCount() == 1) {
                        //single click
                    }
                    else if(e.getClickCount() == 2) {
                        //double click
                        if(selPath.getLastPathComponent().toString().equals("RED Front Left Hatch Auton")){
                            PathPlannerWindow.getInstance().mainPanel.graphWindow.updatePath(AutoPaths.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET);
                        }
                        else if(selPath.getLastPathComponent().toString().equals("BLUE Front Left Hatch Auton")){
                            PathPlannerWindow.getInstance().mainPanel.graphWindow.updatePath(AutoPaths.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET);
                        }
                        else if(selPath.getLastPathComponent().toString().equals("RED Front Right Hatch Auton")){
                            PathPlannerWindow.getInstance().mainPanel.graphWindow.updatePath(AutoPaths.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET);
                        }
                        else if(selPath.getLastPathComponent().toString().equals("BLUE Front Right Hatch Auton")){
                            PathPlannerWindow.getInstance().mainPanel.graphWindow.updatePath(AutoPaths.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET);
                        }

                    }
                }
            }
        };



        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Keycode 13 is enter
                if(e.getKeyCode() == 10){
                    if(currentPath.getLastPathComponent().toString().equals("RED Front Left Hatch Auton")){
                        PathPlannerWindow.getInstance().mainPanel.graphWindow.updatePath(AutoPaths.RED_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET);
                    }
                    else if(currentPath.getLastPathComponent().toString().equals("BLUE Front Left Hatch Auton")){
                        PathPlannerWindow.getInstance().mainPanel.graphWindow.updatePath(AutoPaths.BLUE_LEFT_HATCH_CARGOSHIP_HATCH_ROCKET);
                    }
                    else if(currentPath.getLastPathComponent().toString().equals("RED Front Right Hatch Auton")){
                        PathPlannerWindow.getInstance().mainPanel.graphWindow.updatePath(AutoPaths.RED_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET);
                    }
                    else if(currentPath.getLastPathComponent().toString().equals("BLUE Front Right Hatch Auton")){
                        PathPlannerWindow.getInstance().mainPanel.graphWindow.updatePath(AutoPaths.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };



        addMouseListener(ml);
        addKeyListener(kl);

        setBackground((new Color(80, 80, 80)));

        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

    }
}
