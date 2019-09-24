package com.amhsrobotics.autonomous.graph;

import com.amhsrobotics.autonomous.constants.AutoPaths;
import com.amhsrobotics.autonomous.constants.AutoWaypoints;

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
        setSize(new Dimension(250,200));
        TreeCellRenderer renderer = getCellRenderer();

        DefaultTreeCellRenderer dtcr =
                (DefaultTreeCellRenderer)renderer;
        // Set the various colors
        dtcr.setBackgroundNonSelectionColor((new Color(80, 80, 80)));
        dtcr.setBackgroundSelectionColor(Color.gray);
        dtcr.setTextSelectionColor(Color.white);
        dtcr.setTextNonSelectionColor(new Color(90,199,217));

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
                        selection(selPath.getLastPathComponent().toString());

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
                    selection(currentPath.getLastPathComponent().toString());

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

    private void selection(String pathName){

        PathPlannerWindow.getInstance().getSideBarPanel().getPropertiesPanel().setVisible(false);
        for(int i = 0; i < PathPlannerWindow.getInstance().getSequences().size(); i++){
            if(pathName.equals(PathPlannerWindow.getInstance().getSequences().get(i).getName())){
                PathPlannerWindow.getInstance().setSelectedSequence(PathPlannerWindow.getInstance().getSequences().get(i));
                PathPlannerWindow.getInstance().setGraphType(GraphType.PATH);
                PathPlannerWindow.getInstance().mainPanel.getGraphAutoPath().setPathGraph(PathPlannerWindow.getInstance().getSelectedSequence().getPaths());
                PathPlannerWindow.getInstance().updateWindow();
                PathPlannerWindow.getInstance().getSideBarPanel().getPropertiesPanel().setVisible(true);
                PathPlannerWindow.getInstance().getSideBarPanel().getPropertiesPanel().updateSelection();
            }
            else if(pathName.equals(PathPlannerWindow.getInstance().getSequences().get(i).getName() + " Velocity")){
                PathPlannerWindow.getInstance().setSelectedSequence(PathPlannerWindow.getInstance().getSequences().get(i));
                PathPlannerWindow.getInstance().setGraphType(GraphType.VELOCITY);
                PathPlannerWindow.getInstance().mainPanel.getGraphVelocity().setVelocityGraph(PathPlannerWindow.getInstance().getSelectedSequence().getPaths());
                PathPlannerWindow.getInstance().updateWindow();
                PathPlannerWindow.getInstance().getSideBarPanel().getPropertiesPanel().setVisible(true);
                PathPlannerWindow.getInstance().getSideBarPanel().getPropertiesPanel().updateSelection();
            }
        }




    }
}
