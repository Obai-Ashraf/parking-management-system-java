import javax.swing.*;
import java.awt.*;

public class ParkingGUI extends JFrame {

    JTextField plateField;
    JComboBox<String> typeBox;
    JTextArea outputArea;

    ParkingLot parkingLot = new ParkingLot(5);

    public ParkingGUI() {

        setTitle("Parking Lot System");
        setSize(550, 450);
        setLayout(new FlowLayout());

        add(new JLabel("Plate Number:"));
        plateField = new JTextField(12);
        add(plateField);

        add(new JLabel("Vehicle Type:"));

        String[] types = {"Sedan", "SUV", "Truck"};
        typeBox = new JComboBox<>(types);
        add(typeBox);

        JButton addBtn = new JButton("Add Vehicle");
        JButton removeBtn = new JButton("Remove Vehicle");
        JButton viewBtn = new JButton("View Vehicles");
        JButton searchBtn = new JButton("Search Vehicle");
        JButton exitBtn = new JButton("Exit");

        add(addBtn);
        add(removeBtn);
        add(viewBtn);
        add(searchBtn);
        add(exitBtn);

        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);

        add(new JScrollPane(outputArea));

        addBtn.addActionListener(e -> {

            String plate = plateField.getText();
            String type = (String) typeBox.getSelectedItem();

            outputArea.setText(parkingLot.addVehicle(plate, type));
        });

        removeBtn.addActionListener(e -> {

            String plate = plateField.getText();

            outputArea.setText(parkingLot.removeVehicle(plate));
        });

        viewBtn.addActionListener(e -> {
            outputArea.setText(parkingLot.viewVehicles());
        });

        searchBtn.addActionListener(e -> {

            String plate = plateField.getText();

            outputArea.setText(parkingLot.searchVehicle(plate));
        });

        exitBtn.addActionListener(e -> System.exit(0));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}