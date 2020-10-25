/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package njokiminiproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static sun.swing.SwingUtilities2.submit;

/**
 *
 * @author Wangui Ngunjiri
 */
public class Njokiminiproject extends Application {

    @Override
    public void start(Stage primaryStage) {
        double sceneWidth = 550;
        BorderPane pane = new BorderPane();
        HBox nav = new HBox();
        VBox body = new VBox();

        Text texthome = new Text("Welcome to Cafe Nova!");
        Text textphrase = new Text("You can eat anywhere, but here we give you a dining experience...");
        Button customer_details = new Button("Customer Details/Orders");

        Button location = new Button("Location/Payment Option");

        Button Terms = new Button("Terms and Ratings");
        Button TotalOrders = new Button("Total Orders");//added by jeff
        Button SearchCustomer = new Button("Search Customer");//so this searches details about a customer

        GridPane home = new GridPane();
        home.setMinSize(500, 400);
        home.setPadding(new Insets(8, 8, 8, 8));
        home.setVgap(0);
        home.setHgap(0);
        home.setAlignment(Pos.CENTER);

        home.add(texthome, 1, 0);
        home.add(textphrase, 1, 1, 5, 1);

        home.add(customer_details, 2, 3);
        home.add(location, 3, 3);
        home.add(Terms, 4, 3);
        home.add(TotalOrders, 5, 3);
        home.add(SearchCustomer, 6, 3);

        //styling          
        BackgroundFill background_fill = new BackgroundFill(Color.TURQUOISE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        String nav_itemStyle = "-fx-font: normal 16px Verdana; "
                + "-fx-padding: 10 10 10 10; "
                + "-fx-background-color:#706f6f; "
                + "-fx-border-color: #424242; "
                + "-fx-border-width: 1px 1px 1px 1px; "
                + "-fx-text-fill: white;";
        String labelStyle = "-fx-font: normal 16px Verdana; ";
        String normalStyle = "-fx-font: normal 12px Verdana; ";
        String msgStyle = "-fx-font: bold 36px 'Dancing Script'; "
                + "-fx-text-fill: #ccff99; ";

        // border pane
        pane.setTop(body);
        pane.setStyle("-fx-background-color: #c7c5c5; ");
        //styling buttons
        customer_details.setStyle(nav_itemStyle);

        location.setStyle(nav_itemStyle);
        TotalOrders.setStyle(nav_itemStyle);
        SearchCustomer.setStyle(nav_itemStyle);

        Terms.setStyle(nav_itemStyle);
        home.setBackground(background);

        //styling font
        texthome.setStyle(msgStyle);
        textphrase.setStyle(labelStyle);
        Scene scene = new Scene(home);
        primaryStage.setTitle("HOME PAGE");
        primaryStage.setScene(scene);
        primaryStage.show();

        customer_details.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                //CUSTOMER DETAILS
                Text fname = new Text("First Name");
                TextField name = new TextField();
                Text sname = new Text("Surname");
                TextField surname = new TextField();
                Text Pnumber = new Text("Phone Number");
                TextField phone = new TextField();
                Text email = new Text("Email address");
                TextField mail = new TextField();
                Button storedata = new Button("Done");
                Button movetoorder = new Button("Take order");
                Text Intro = new Text("Kindly fill in your details and proceed to take your order");
                Text txt1 = new Text();

                GridPane gridpane = new GridPane();
                gridpane.setMinSize(600, 400);
                gridpane.setPadding(new Insets(10, 10, 10, 10));
                gridpane.setVgap(10);
                gridpane.setHgap(10);
                gridpane.setAlignment(Pos.TOP_LEFT);

                gridpane.add(Intro, 0, 0, 2, 1);
                gridpane.add(fname, 0, 1);
                gridpane.add(name, 1, 1);
                gridpane.add(sname, 0, 2);
                gridpane.add(surname, 1, 2);
                gridpane.add(Pnumber, 0, 3);
                gridpane.add(phone, 1, 3);
                gridpane.add(email, 0, 4);
                gridpane.add(mail, 1, 4);
                gridpane.add(storedata, 0, 5);
                gridpane.add(movetoorder, 3, 5);
                gridpane.add(txt1, 1, 6);
                //styling
                storedata.setStyle(nav_itemStyle);
                movetoorder.setStyle(nav_itemStyle);
                fname.setStyle(normalStyle);
                sname.setStyle(normalStyle);
                Pnumber.setStyle(normalStyle);
                email.setStyle(normalStyle);
                Intro.setStyle(labelStyle);
                gridpane.setBackground(background);

                Stage newWindow1 = new Stage();
                newWindow1.setTitle("Customer Details");
                Scene Cscene = new Scene(gridpane);
                newWindow1.setScene(Cscene);
                newWindow1.show();

                storedata.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
                    public void handle(javafx.scene.input.MouseEvent event) {
                        try {

                            final String fname = name.getText();
                            final String sname = surname.getText();
                            final String Pnumber = phone.getText();
                            final String email = mail.getText();

                            Class.forName("com.mysql.jdbc.Driver"); //step one                
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafenova?autoReconnect=true&useSSL=false", "root", "");  //step two

                            Statement st = con.createStatement();   //step three                
                            String sts = "INSERT INTO `customerdetails`(`fname`,`surname`,`Phone Number`,`Email`) VALUES (" + "'" + fname + "'" + "," + "'" + sname + "'" + "," + "'" + Pnumber + "'" + "," + "'" + email + "'" + ")";

                            st.executeUpdate(sts); //step four
                            txt1.setText("Karibu Sana!");

                            con.close();//step five
                        } catch (Exception ee) {
                            System.out.println(ee);
                            txt1.setText("Not Inserted");
                        }

                    }
                }));
                movetoorder.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        newWindow1.close();
                        //MENU
                        ChoiceBox appetizer = new ChoiceBox();
                        appetizer.getItems().addAll("Pumpkin Soup", "Beef Broth", "Tomato soup");
                        ChoiceBox Lunch_dinner = new ChoiceBox();
                        Lunch_dinner.getItems().addAll("Succotash", "Grilled Chicken Salad", "Lamb Chops");
                        ChoiceBox Drink = new ChoiceBox();
                        Drink.getItems().addAll("Water", "Juice", "Wine", "MilkShake");
                        ChoiceBox Desert = new ChoiceBox();
                        Desert.getItems().addAll("Ice Cream", "Cake", "Tiramisu");

                        Text text1 = new Text("Appetizers");
                        Text text2 = new Text("Lunch/Dinner");
                        Text text3 = new Text("Drinks");
                        Text text4 = new Text("Desert");
                        Text intro = new Text("You may now take your order");
                        Text statement = new Text();
                        Button order = new Button("Order");

                        GridPane Menu = new GridPane();
                        Menu.setMinSize(600, 300);
                        Menu.setPadding(new Insets(10, 10, 10, 10));
                        Menu.setVgap(10);
                        Menu.setHgap(10);
                        Menu.setAlignment(Pos.CENTER);

                        Menu.add(intro, 0, 0, 2, 1);
                        Menu.add(text1, 0, 1);

                        Menu.add(appetizer, 1, 1);
                        Menu.add(text2, 0, 2);
                        Menu.add(Lunch_dinner, 1, 2);
                        Menu.add(text3, 0, 3);
                        Menu.add(Drink, 1, 3);
                        Menu.add(text4, 0, 4);
                        Menu.add(Desert, 1, 4);
                        Menu.add(order, 2, 5);
                        Menu.add(statement, 0, 7);

                        //styling
                        order.setStyle(nav_itemStyle);
                        text1.setStyle(normalStyle);
                        text2.setStyle(normalStyle);
                        text3.setStyle(normalStyle);
                        text4.setStyle(normalStyle);
                        intro.setStyle(labelStyle);
                        Menu.setBackground(background);
                        Stage newWindow1 = new Stage();
                        newWindow1.setTitle("Menu");
                        Scene Mscene = new Scene(Menu);
                        newWindow1.setScene(Mscene);
                        newWindow1.show();

                        order.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
                            public void handle(javafx.scene.input.MouseEvent event) {
                                {
                                    final String appetizer_choice = (String) appetizer.getValue();
                                    final String meal_choice = (String) Lunch_dinner.getValue();
                                    final String drink_choice = (String) Drink.getValue();
                                    final String desert_choice = (String) Desert.getValue();

                                    Text intro = new Text("Thank you for ordering from Cafe Nova");
                                    Text statement = new Text();
                                    statement.setText("Your order is: " + appetizer_choice + ", " + meal_choice + " , " + drink_choice + " ," + desert_choice + " and the total cost is: 140$");
                                    Button close = new Button("Close");

                                    GridPane invoice = new GridPane();
                                    invoice.setMinSize(600, 200);
                                    invoice.setPadding(new Insets(10, 10, 10, 10));
                                    invoice.setHgap(1);
                                    invoice.setVgap(5);
                                    invoice.setAlignment(Pos.CENTER);

                                    invoice.add(intro, 0, 0, 7, 1);
                                    invoice.add(statement, 0, 1, 7, 1);
                                    invoice.add(close, 7, 2);

                                    Scene scene = new Scene(invoice);
                                    Stage invoice1 = new Stage();
                                    invoice1.setTitle("Invoice");
                                    invoice1.setScene(scene);
                                    invoice1.show();
                                    newWindow1.close();
                                    invoice.setBackground(background);
                                    close.setStyle(nav_itemStyle);
                                    statement.setStyle(labelStyle);

                                    intro.setStyle(labelStyle);
                                    close.setOnMouseClicked((new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            invoice1.close();

                                        }
                                    }));

                                }
                            }

                        }));

                    }
                });

            }

        });

        location.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent event) {
                //LOCATION &DELIVERY DETAILS
                Text Region = new Text("Region");
                ChoiceBox cregion = new ChoiceBox();
                cregion.getItems().addAll("CBD", "Dagoretti", " Embakasi", " Kasarani", " Kibera", " Makadara", " Pumwani", " Westlands", " Karen", " Langata", " Lavington", " Gigiri", " Muthaiga", " Brookside", "Spring Valley", " Loresho", " Kilimani", " Kileleshwa", " Hurlingham", " Runda", " Kitisuru");
                Text street = new Text("Street/Road");
                TextField cstreet = new TextField();
                Text estate = new Text("Estate/Villa/Apartment");
                TextField cestate = new TextField();
                Text houseno = new Text("House Number");
                TextField chouseno = new TextField();
                Text Info = new Text("Fill in the following your location details for your order to be delivered");
                Button submit = new Button("Submit");

                GridPane delivery = new GridPane();
                delivery.setMinSize(600, 400);
                delivery.setPadding(new Insets(10, 10, 10, 10));
                delivery.setVgap(10);
                delivery.setHgap(10);
                delivery.setAlignment(Pos.CENTER);

                delivery.add(Info, 0, 0, 2, 1);
                delivery.add(Region, 0, 1);
                delivery.add(cregion, 1, 1);
                delivery.add(street, 0, 2);
                delivery.add(cstreet, 1, 2);
                delivery.add(estate, 0, 3);
                delivery.add(cestate, 1, 3);
                delivery.add(houseno, 0, 4);
                delivery.add(chouseno, 1, 4);
                delivery.add(submit, 0, 5);
                //styling
                submit.setStyle(nav_itemStyle);
                Region.setStyle(normalStyle);
                street.setStyle(normalStyle);
                estate.setStyle(normalStyle);
                houseno.setStyle(normalStyle);
                Info.setStyle(labelStyle);
                delivery.setBackground(background);
                Stage newWindow2 = new Stage();
                newWindow2.setTitle("Delivery Details");
                Scene Dscene = new Scene(delivery);
                newWindow2.setScene(Dscene);
                newWindow2.show();

                submit.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        //PAYMENT OPTIONS
                        Text payment = new Text("Payment method");
                        ChoiceBox mpayment = new ChoiceBox();
                        mpayment.getItems().addAll("Cash", "M-pesa", "Visa/ Debit Card");
                        Text user = new Text("*Kindly choose your  method of payment:");
                        Button mpsubmit = new Button("Submit");
                        GridPane pmethod = new GridPane();
                        pmethod.setMinSize(600, 400);
                        pmethod.setPadding(new Insets(10, 10, 10, 10));
                        pmethod.setVgap(10);
                        pmethod.setHgap(10);
                        pmethod.setAlignment(Pos.CENTER);

                        pmethod.add(user, 0, 0, 2, 1);
                        pmethod.add(payment, 0, 1);
                        pmethod.add(mpayment, 1, 1);
                        pmethod.add(mpsubmit, 0, 2);

                        //style
                        payment.setStyle(normalStyle);
                        user.setStyle(normalStyle);
                        mpsubmit.setStyle(nav_itemStyle);
                        user.setStyle(labelStyle);
                        pmethod.setBackground(background);

                        Stage newWindow = new Stage();
                        newWindow.setTitle("Payment Method");
                        Scene scene_two = new Scene(pmethod);
                        newWindow.setScene(scene_two);
                        newWindow.show();
                        newWindow2.close();
                        mpsubmit.setOnMouseClicked((new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                newWindow.close();

                            }
                        }));

                    }
                });

            }
        }));

        SearchCustomer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Text CustomerNo = new Text("Enter Customer No");
                TextField custNo = new TextField();//input for customer No
                Button searchCust = new Button("Search Customer!");// searchCust is basically search customer
                Text FirstName = new Text();
                Text FnamePlh = new Text();// plh means placeholder
                Text Surname = new Text();
                Text SurnamePlh = new Text();
                Text PhoneNo = new Text();
                Text PhonePlh = new Text();
                Text Email = new Text();
                Text EmailPlh = new Text();
                Text Error = new Text();
                Text Title = new Text("SEARCH CUSTOMER DETAILS");

                GridPane CustomerGrid = new GridPane();
                CustomerGrid.setMinSize(600, 400);
                CustomerGrid.setPadding(new Insets(10, 10, 10, 10));
                CustomerGrid.setVgap(10);
                CustomerGrid.setHgap(10);
                CustomerGrid.setAlignment(Pos.CENTER);

                CustomerGrid.add(Title, 0, 0, 2, 1);
                CustomerGrid.add(CustomerNo, 0, 1);
                CustomerGrid.add(custNo, 1, 1);
                CustomerGrid.add(searchCust, 1, 2);
                CustomerGrid.add(FirstName, 0, 4);
                CustomerGrid.add(FnamePlh, 1, 4);
                CustomerGrid.add(Surname, 0, 5);
                CustomerGrid.add(SurnamePlh, 1, 5);
                CustomerGrid.add(PhoneNo, 0, 6);
                CustomerGrid.add(PhonePlh, 1, 6);
                CustomerGrid.add(Email, 0, 7);
                CustomerGrid.add(EmailPlh, 1, 7);
                CustomerGrid.add(Error, 1, 8);

                //styling
                searchCust.setStyle(nav_itemStyle);
                FnamePlh.setStyle(normalStyle);
                FirstName.setStyle(normalStyle);
                custNo.setStyle(normalStyle);
                CustomerNo.setStyle(normalStyle);
                Email.setStyle(normalStyle);
                EmailPlh.setStyle(normalStyle);
                Error.setStyle(normalStyle);
                Surname.setStyle(normalStyle);
                SurnamePlh.setStyle(normalStyle);
                Title.setStyle(labelStyle);
                CustomerGrid.setBackground(background);

                searchCust.setOnMouseClicked((new EventHandler<javafx.scene.input.MouseEvent>() {
                    public void handle(javafx.scene.input.MouseEvent event) {
                        try {

                            final String theCustNo = custNo.getText();

                            Class.forName("com.mysql.jdbc.Driver"); //step one                
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafenova?autoReconnect=true&useSSL=false", "root", "");  //step two

                            Statement st = con.createStatement();   //step three  
                            // DELETE FROM `borrowedlist` WHERE 0

                            String sts = "select * from customerdetails where CustomerNo" + "=" + "'" + theCustNo + "'";

                            ResultSet rs = st.executeQuery(sts);

                            while (rs.next()) {

                                String fName = rs.getString("fName");
                                String EmailTxt = rs.getString("Email");
                                String SurnameTxt = rs.getString("Surname");
                                String PhoneTxt = rs.getString("Phone Number");
                                FirstName.setText("FirstName");
                                FnamePlh.setText(fName);
                                Surname.setText("Surname");
                                SurnamePlh.setText(SurnameTxt);
                                PhoneNo.setText("PhoneNo:");
                                PhonePlh.setText(PhoneTxt);
                                Email.setText("Email");
                                EmailPlh.setText(EmailTxt);

                            }

                            con.close();//step five
                        } catch (Exception ee) {
                            Error.setText("Error! The customer number does not exsist");
                            System.out.println(ee);

                            //txt1.setText("Seems to have experienced an error!");
                        }

                    }
                }));
                //cust is just short for customer
                Stage CustWind = new Stage();
                CustWind.setTitle("Customer Details");
                Scene CustScene = new Scene(CustomerGrid);
                CustWind.setScene(CustScene);
                CustWind.show();

            }
        });

        Terms.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Terms and conditions
                Text TermsandCondition = new Text("1. APPLICABILITY\n"
                        + "1.1 The General Terms and Conditions below apply to all offers and transactions of Cafe Nova. Prices are subject to change.\n"
                        + "1.2 By accepting an offer or making an order, the consumer expressly accepts the applicability of these General Terms and Conditions.\n"
                        + "1.3 Deviations from that stipulated in these Terms and Conditions are only valid when they are confirmed in writing by the management.\n"
                        + "1.4 All rights and entitlements stipulated for Cafe Nova in these General Terms and Conditions and any further agreements will also apply for intermediaries and other third parties deployed by Blue Pepper.\n"
                        + "\n"
                        + "2. QUALITY\n"
                        + "The restaurant guarantees that all the products offered meet the standards of the concept.\n"
                        + "If there are any complaints the management needs to be informed immediately. Appropriate actions will be taken as soon as possible.\n"
                        + "\n"
                        + "3. PRICES/OFFERS\n"
                        + "3.1 All offers made by Cafe Nova are without obligation and Cafe Nova expressly reserves the right to change the prices, in particular if this is necessary as a result of statutory or other regulations.\n"
                        + "3.1 All prices are indicated in dolars, including VAT.\n"
                        + "3.3 In certain cases, promotional prices apply. These prices are valid only during a specific period as long as stocks last. No entitlement to these prices may be invoked before or after the specific period.\n"
                        + "3.4 Cafe Nova cannot be held to any price indications that are clearly incorrect, for example as a result of obvious typesetting or printing errors. No rights may be derived from incorrect price information.\n"
                        + "\n"
                        + "4. CANCELLATIONS\n"
                        + "4.1 Cafe Nove is entitled to cancel or change the date of an event. Should this happen, Cafe Nova will attempt to provide a suitable solution. If an event is cancelled or postponed, Cafe Nova will do its utmost to inform you as soon as possible. However, Cafe Nova cannot guarantee it is possible to inform you timely of any change or cancellation of an event or be held responsible for refunds, compensations or for any resulting costs you may incur, for example for travel, accommodation and/or any other related goods or service.\n"
                        + "4.2 Before confirming your reservation, always check carefully that you have reserved the correct (number of) persons. Wrongfully ordered (numbers of) persons are not refundable.\n"
                        + "4.3. All purchases are final. The dinner tickets bought here cannot be returned for any refunds whatsoever; group bookings paid for on the website likewise cannot be cancelled by the purchaser and refunds claimed for any reason whatsoever.\n"
                        + "\n"
                        + "5. PAYMENTS\n"
                        + "5.1 All prices are including VAT.\n"
                        + "5.2 Methods of payment we accept: Cash, Mpesa and Card.\n"
                        + "5.3 You will not receive confirmation of your delivery until your payment has been approved.\n"
                        + "\n"
                        + "6. OTHER PROVISIONS\n"
                        + "6.1  If one or more of the provisions in these Terms and Conditions or any other agreement with Cafe Nova are in conflict with any applicable legal regulation, the provision in question will lapse and be replaced by a new comparable stipulation admissible by law to be determined by Cafe Nova.\n"
                        + "6.2  The law of Kenya applies to all agreements entered into with or concluded by Cafe Nova. Any disputes arising directly or indirectly from these agreements will be exclusively settled by the Kenyan Judicial Sysytem.");
                RadioButton radioButton1 = new RadioButton("Agree");
                RadioButton radioButton2 = new RadioButton("Disagree");
                Button next = new Button("Next");
                Text heading = new Text("Term And Conditions");
                ToggleGroup radioGroup = new ToggleGroup();
                radioButton1.setToggleGroup(radioGroup);
                radioButton2.setToggleGroup(radioGroup);

                GridPane terms = new GridPane();
                terms.setMinSize(500, 400);
                terms.setPadding(new Insets(8, 8, 8, 8));
                terms.setVgap(5);
                terms.setHgap(0);
                terms.setAlignment(Pos.CENTER);

                terms.add(TermsandCondition, 1, 1, 10, 35);
                terms.add(radioButton1, 1, 36);
                terms.add(radioButton2, 1, 37);
                terms.add(heading, 1, 0);
                terms.add(next, 1, 38);

                //styling
                heading.setStyle(msgStyle);
                terms.setBackground(background);
                next.setStyle(nav_itemStyle);

                Stage newWindow4 = new Stage();
                newWindow4.setTitle("Ratings");
                Scene Tscene = new Scene(terms);
                newWindow4.setScene(Tscene);
                newWindow4.show();
                next.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        RadioButton radioButtonOne = new RadioButton("1 star(*)");
                        RadioButton radioButtonTwo = new RadioButton("2 star(**)");
                        RadioButton radioButton3 = new RadioButton("3 star(***)");
                        RadioButton radioButton4 = new RadioButton("4 star(****)");
                        RadioButton radioButton5 = new RadioButton("5 star(*****)");
                        Text motto = new Text("Njoki's eatery, your one stop premium diner of choice");
                        Text anotherone = new Text("Kindly give us your rating on our services on a scale of 1-5 ");
                        Text comment = new Text("Thank you for dining with us");
                        Button ratingclose = new Button("Done");

                        GridPane rate = new GridPane();
                        rate.setMinSize(600, 400);
                        rate.setPadding(new Insets(10, 10, 10, 10));
                        rate.setVgap(10);
                        rate.setHgap(10);
                        rate.setAlignment(Pos.CENTER);

                        rate.add(comment, 0, 0);
                        rate.add(motto, 0, 1);
                        rate.add(anotherone, 0, 2);
                        rate.add(radioButtonOne, 0, 3);
                        rate.add(radioButtonTwo, 0, 4);
                        rate.add(radioButton3, 0, 5);
                        rate.add(radioButton4, 0, 6);
                        rate.add(radioButton5, 0, 7);
                        rate.add(ratingclose, 1, 8);
                        ratingclose.setStyle(nav_itemStyle);
                        motto.setStyle(normalStyle);
                        anotherone.setStyle(normalStyle);
                        comment.setStyle(normalStyle);

                        ToggleGroup radioGroup1 = new ToggleGroup();
                        radioButtonOne.setToggleGroup(radioGroup1);
                        radioButtonTwo.setToggleGroup(radioGroup1);
                        radioButton3.setToggleGroup(radioGroup1);
                        radioButton4.setToggleGroup(radioGroup1);

                        rate.setBackground(background);

                        Stage newWindow5 = new Stage();
                        newWindow5.setTitle("Ratings");
                        Scene Rscene = new Scene(rate);
                        newWindow5.setScene(Rscene);
                        newWindow5.show();
                        newWindow4.close();
                        ratingclose.setOnMouseClicked((new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                newWindow5.close();
                                primaryStage.close();

                            }
                        }));

                    }
                }));

            }
        }));

        // Stage
        primaryStage.setTitle("Cafe Nova");
        primaryStage.setX(5);
        primaryStage.setY(10);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
