package Xinyuiii.MansionGenerator.properties;

import Xinyuiii.MansionGenerator.piece.Piece;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.item.ItemStack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class Main extends JFrame {
    private JTextArea consoleArea;
    private JTextField seedField;
    private JTextField xPosField;
    private JTextField zPosField;
    public static final MCVersion version = MCVersion.v1_20;

    public Main() {
        // Dark theme setup
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            
            // Custom dark theme colors
            UIManager.put("control", new Color(45, 45, 45));
            UIManager.put("text", Color.WHITE);
            UIManager.put("nimbusBase", new Color(30, 30, 30));
            UIManager.put("nimbusAlertYellow", new Color(255, 200, 0));
            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
            UIManager.put("nimbusFocus", new Color(80, 120, 180));
            UIManager.put("nimbusGreen", new Color(100, 150, 50));
            UIManager.put("nimbusInfoBlue", new Color(50, 100, 180));
            UIManager.put("nimbusLightBackground", new Color(45, 45, 45));
            UIManager.put("nimbusSelectionBackground", new Color(80, 80, 120));
            
            // Specific tab customization
            UIManager.put("TabbedPane.background", new Color(30, 30, 30));
            UIManager.put("TabbedPane.foreground", Color.WHITE);
            UIManager.put("TabbedPane.contentAreaColor", new Color(30, 30, 30));
            UIManager.put("TabbedPane.selected", new Color(50, 50, 70));
            UIManager.put("TabbedPane.tabAreaBackground", new Color(30, 30, 30));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Woodland Mansion Checker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        // Create custom tabbed pane with dark theme
        JTabbedPane tabbedPane = new JTabbedPane() {
            @Override
            public void updateUI() {
                super.updateUI();
                setBackground(new Color(30, 30, 30));
                setForeground(Color.WHITE);
                setOpaque(true);
            }
        };
        tabbedPane.setBackground(new Color(30, 30, 30));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setOpaque(true);

        // Main panel with dark background
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(30, 30, 30));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        mainPanel.setOpaque(false);
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Input panel with modern dark styling
        JPanel inputPanel = createModernInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Console area with dark theme
        consoleArea = new JTextArea();
        consoleArea.setBackground(new Color(45, 45, 45));
        consoleArea.setForeground(Color.WHITE);
        consoleArea.setCaretColor(Color.WHITE);
        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);
        scrollPane.getViewport().setBackground(new Color(45, 45, 45));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 2));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add tabs with dark theme
        tabbedPane.addTab("Mansion Checker", mainPanel);
        
        // About panel with dark theme
        JPanel aboutPanel = createModernAboutPanel();
        tabbedPane.addTab("About", aboutPanel);

        // Ensure entire tabbed pane is dark
        tabbedPane.setBackground(new Color(30, 30, 30));
        
        add(tabbedPane);
    }

    private JPanel createModernInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(30, 30, 30));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        inputPanel.setOpaque(false);
        inputPanel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Modern, clean fonts
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font inputFont = new Font("Consolas", Font.PLAIN, 14);

        // Seed input
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel seedLabel = new JLabel("Seed:");
        seedLabel.setForeground(Color.WHITE);
        seedLabel.setFont(labelFont);
        inputPanel.add(seedLabel, gbc);
        
        gbc.gridx = 1;
        seedField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(60, 60, 60));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        seedField.setOpaque(false);
        seedField.setFont(inputFont);
        seedField.setForeground(Color.WHITE);
        seedField.setCaretColor(Color.WHITE);
        seedField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        inputPanel.add(seedField, gbc);

        // X Position input
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel xLabel = new JLabel("X Position:");
        xLabel.setForeground(Color.WHITE);
        xLabel.setFont(labelFont);
        inputPanel.add(xLabel, gbc);
        
        gbc.gridx = 1;
        xPosField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(60, 60, 60));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        xPosField.setOpaque(false);
        xPosField.setFont(inputFont);
        xPosField.setForeground(Color.WHITE);
        xPosField.setCaretColor(Color.WHITE);
        xPosField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        inputPanel.add(xPosField, gbc);

        // Z Position input
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel zLabel = new JLabel("Z Position:");
        zLabel.setForeground(Color.WHITE);
        zLabel.setFont(labelFont);
        inputPanel.add(zLabel, gbc);
        
        gbc.gridx = 1;
        zPosField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(60, 60, 60));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        zPosField.setOpaque(false);
        zPosField.setFont(inputFont);
        zPosField.setForeground(Color.WHITE);
        zPosField.setCaretColor(Color.WHITE);
        zPosField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        inputPanel.add(zPosField, gbc);

        // Generate button with modern styling
        gbc.gridx = 1; gbc.gridy = 3;
        JButton generateButton = new JButton("Check Mansion") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(80, 80, 120));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        generateButton.setOpaque(false);
        generateButton.setContentAreaFilled(false);
        generateButton.setBorderPainted(false);
        generateButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateMansion();
            }
        });
        inputPanel.add(generateButton, gbc);

        return inputPanel;
    }

    private JPanel createModernAboutPanel() {
        JPanel aboutPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(30, 30, 30));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        aboutPanel.setBackground(new Color(30, 30, 30));
        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
        aboutPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Title with modern styling
        JLabel titleLabel = new JLabel("Woodland Mansion Checker");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Project details with subtle colors
        JLabel projectOwnerLabel = new JLabel("Project Owner: XinYuQiii");
        projectOwnerLabel.setForeground(new Color(180, 180, 180));
        projectOwnerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        projectOwnerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel guiByLabel = new JLabel("GUI Design: MZEEN");
        guiByLabel.setForeground(new Color(180, 180, 180));
        guiByLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        guiByLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Version information
        JLabel versionLabel = new JLabel("Version: 1.0.0");
        versionLabel.setForeground(new Color(150, 150, 150));
        versionLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components with vertical spacing
        aboutPanel.add(Box.createVerticalStrut(50));
        aboutPanel.add(titleLabel);
        aboutPanel.add(Box.createVerticalStrut(30));
        aboutPanel.add(projectOwnerLabel);
        aboutPanel.add(Box.createVerticalStrut(10));
        aboutPanel.add(guiByLabel);
        aboutPanel.add(Box.createVerticalStrut(10));
        aboutPanel.add(versionLabel);

        return aboutPanel;
    }

    private void generateMansion() {
        try {
            consoleArea.setText(""); // Clear previous output
            
            long seed = Long.parseLong(seedField.getText().trim());
            int x = Integer.parseInt(xPosField.getText().trim());
            int z = Integer.parseInt(zPosField.getText().trim());

            ChunkRand rand = new ChunkRand();
            CPos pos = (new BPos(x, 0, z)).toChunkPos();
            
            MansionGenerator generator = new MansionGenerator(version);
            generator.generateSkipHeight(seed, pos, rand);
            generator.generateDecoration();

            // Count rooms by floor
            int firstFloorRooms = 0;
            int secondFloorRooms = 0;
            int thirdFloorRooms = 0;

            // Print rooms with detailed y-coordinate logging
            consoleArea.append("=== Woodland Mansion Rooms ===\n");
            for (Piece room : generator.getAllRooms()) {
                // Debug: print full room details without Y coordinate
                consoleArea.append(String.format("Room: %s, Full Coordinates: %s\n", 
                    room.name, room.pos));
                
                // Categorize rooms by y-coordinate with precise matching
                if (room.pos.getY() >= 64 && room.pos.getY() < 72) {
                    firstFloorRooms++;
                } else if (room.pos.getY() >= 72 && room.pos.getY() < 83) {
                    secondFloorRooms++;
                } else if (room.pos.getY() >= 83) {
                    thirdFloorRooms++;
                }
            }

            // Room count summary
            int totalRooms = generator.getAllRooms().size();
            consoleArea.append("\n=== Room Count Summary ===\n");
            consoleArea.append(String.format("• Total Rooms: %d\n", totalRooms));
            consoleArea.append(String.format("• First Floor Rooms: %d\n", firstFloorRooms));
            consoleArea.append(String.format("• Second Floor Rooms: %d\n", secondFloorRooms));
            consoleArea.append(String.format("• Third Floor Rooms: %d\n", thirdFloorRooms));

            // Print chests
            consoleArea.append("\n=== Chest Locations ===\n");
            for (Pair<BPos, List<ItemStack>> chest : generator.getChests()) {
                consoleArea.append("\nChest at: " + chest.getFirst() + "\n");
                for (ItemStack itemStack : chest.getSecond()) {
                    consoleArea.append(String.format("  %d x %s\n", 
                        itemStack.getCount(), itemStack.getItem().getName()));
                }
            }
        } catch (NumberFormatException e) {
            consoleArea.setText("Error: Please enter valid numbers for seed and coordinates.");
        } catch (Exception e) {
            consoleArea.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}