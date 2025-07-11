import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class retailstoremanagementsystem extends JFrame {
    static int stockLevel = 0;  
    static double totalSales = 0.0;  

    public retailstoremanagementsystem() {
        setTitle("Inventory Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));  
        getContentPane().setBackground(Color.CYAN);  

        
        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddProductFrame().setVisible(true);
                setVisible(false);  
            }
        });

        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlaceOrderFrame().setVisible(true);
                setVisible(false);  
            }
        });

        
        JLabel projectNameLabel = new JLabel("Inventory and Order Management");
        projectNameLabel.setFont(new Font("Arial", Font.BOLD, 18));

      
        add(projectNameLabel);
        add(addProductButton);
        add(placeOrderButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            retailstoremanagementsystem frame = new retailstoremanagementsystem();
            frame.setVisible(true);
        });
    }
}


class AddProductFrame extends JFrame {
    private JTextField productNameField, quantityField, priceField;
    private JTextArea displayArea;
    private JButton addProductButton, backButton;

    public AddProductFrame() {
        setTitle("Add Product");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));  
        getContentPane().setBackground(Color.LIGHT_GRAY);  

      
        productNameField = new JTextField();
        quantityField = new JTextField();
        priceField = new JTextField();
        displayArea = new JTextArea(5, 30);
        displayArea.setEditable(false);

        addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String productName = productNameField.getText();
                    int quantity = Integer.parseInt(quantityField.getText());
                    double price = Double.parseDouble(priceField.getText());

                
                    retailstoremanagementsystem.stockLevel += quantity;
                    displayArea.append("Added Product: " + productName + " | Quantity: " + quantity + " | Price: $" + price + "\n");
                    displayArea.append("Current Stock Level: " + retailstoremanagementsystem.stockLevel + "\n");

                  
                    productNameField.setText("");
                    quantityField.setText("");
                    priceField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid quantity and price.");
                }
            }
        });

        
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new retailstoremanagementsystem().setVisible(true);  
            setVisible(false);  
        });

        
        add(new JLabel("Product Name:"));
        add(productNameField);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("Price:"));
        add(priceField);
        add(addProductButton);
        add(backButton);
        add(new JScrollPane(displayArea));
    }
}


class PlaceOrderFrame extends JFrame {
    private JTextField customerNameField, quantityField, priceField;
    private JTextArea displayArea;
    private JButton placeOrderButton, backButton;

    public PlaceOrderFrame() {
        setTitle("Place Order");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));  
        getContentPane().setBackground(Color.PINK);  

     
        customerNameField = new JTextField();
        quantityField = new JTextField();
        priceField = new JTextField();
        displayArea = new JTextArea(5, 30);
        displayArea.setEditable(false);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String customerName = customerNameField.getText();
                    int orderQuantity = Integer.parseInt(quantityField.getText());

                    if (orderQuantity <= retailstoremanagementsystem.stockLevel) {
                        
                        retailstoremanagementsystem.stockLevel -= orderQuantity;
                        double totalPrice = orderQuantity * Double.parseDouble(priceField.getText());
                        retailstoremanagementsystem.totalSales += totalPrice;

                        displayArea.append("Order placed by " + customerName + " | Quantity: " + orderQuantity + " | Total: $" + totalPrice + "\n");
                        displayArea.append("Remaining Stock Level: " + retailstoremanagementsystem.stockLevel + "\n");
                        displayArea.append("Total Sales: $" + retailstoremanagementsystem.totalSales + "\n");

                       
                        customerNameField.setText("");
                        quantityField.setText("");
                        priceField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Not enough stock for this order.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid order quantity and price.");
                }
            }
        });

        
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new retailstoremanagementsystem().setVisible(true); 
            setVisible(false);  
        });

       
        add(new JLabel("Customer Name:"));
        add(customerNameField);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("Price:"));
        add(priceField);
        add(placeOrderButton);
        add(backButton);
        add(new JScrollPane(displayArea));
    }
}

