package com.example.android.thingsfive;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity{

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */


    public void submitOrder(View view) {
        EditText nameField =  (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("MainActivity", "Name: " + name);

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.wipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);


        Boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Boolean hasChocolate = chocolateCheckBox.isChecked();

        Log.v("MainActivity", "Has Wipped Cream: " + hasWhippedCream);
        Log.v("MainActivity", "Has Wipped Cream: " + hasChocolate);


        int price = calculatePrice();
        String priceMessage = createOrderSummary(name,price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        // only email app should handle this
        intent.putExtra(intent.EXTRA_SUBJECT, "Just Java Order For: " + name);
        intent.putExtra(intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) !=null) {
            startActivity(intent);
        }

        displayMessage(priceMessage);

    }

    // Criando botoes, o nome do metodo tem que ser o mesmo do onClick
    public void moneys (View view){

        setContentView(R.layout.money1);
    }

    public void works (View view){

        setContentView(R.layout.work1);
    }

    public void backmoney (View view){

        setContentView(R.layout.activity_main);
    }

    public void backwork (View view){

        setContentView(R.layout.activity_main);
    }

    public void houses (View view){

        setContentView(R.layout.house1);
    }

    public void backhouse (View view){

        setContentView(R.layout.activity_main);
    }

    public void cars (View view){

        setContentView(R.layout.car1);
    }
    public void backcar (View view){

        setContentView(R.layout.activity_main);
    }

    public void friends (View view){

        setContentView(R.layout.friends1);
    }

    public void backfriends (View view){

        setContentView(R.layout.activity_main);
    }



    /**
     * Calculates the price of the order.
     *
     * @param //quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {


        return quantity;
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffes) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffes);

    }

    public void increment(View view) {
        if(quantity == 100){
            //Show an error message
            Toast.makeText(this, "You cannot have more 100 coffes", Toast.LENGTH_SHORT).show();
            //Exit this method early because there´s nothing left to do
            return;
        } quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1){
            //Show an error message
            Toast.makeText(this, "You cannot have lass than 1 coffe", Toast.LENGTH_SHORT).show();
            //Exit this method early because there´s nothing left to do
            return;
        } quantity = quantity - 1;
        displayQuantity(quantity);
    }

    private String createOrderSummary(String name, int price, Boolean addWhippedCream, Boolean addChocolate){

        String priceMessage = " " + name;
        priceMessage += "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity : " + quantity ;
        priceMessage += "\nTotal $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }

}