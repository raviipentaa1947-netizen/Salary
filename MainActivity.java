package com.example.salarycalculator;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText baseSalaryInput, taxInput, medicalInput, leaveInput, dabbaInput;
    LinearLayout resultsBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseSalaryInput = findViewById(R.id.base_salary);
        taxInput = findViewById(R.id.tax_amount);
        medicalInput = findViewById(R.id.medical_amount);
        leaveInput = findViewById(R.id.leave_days);
        dabbaInput = findViewById(R.id.dabba_units);
        resultsBox = findViewById(R.id.results_box);

        Button calcButton = findViewById(R.id.calc_btn);
        calcButton.setOnClickListener(v -> calculate());
    }

    private void calculate() {
        try {
            double baseSalary = parseDouble(baseSalaryInput.getText().toString());
            double tax = parseDouble(taxInput.getText().toString());
            double medical = parseDouble(medicalInput.getText().toString());
            int leaveDays = parseInt(leaveInput.getText().toString());
            int dabbaUnits = parseInt(dabbaInput.getText().toString());

            double perDaySalary = baseSalary / 30.0;
            double fifthMondayBonus = hasFifthMonday() ? perDaySalary : 0.0;
            double salaryWithBonus = baseSalary + fifthMondayBonus;
            double pfDeduction = baseSalary * 0.10;
            double dabbaDeduction = dabbaUnits * 30;
            double leaveDeduction = perDaySalary * leaveDays;
            double totalDeductions = tax + medical + pfDeduction + dabbaDeduction + leaveDeduction;
            double netMonthly = salaryWithBonus - totalDeductions;

            resultsBox.removeAllViews();
            addResult("Salary for", java.time.LocalDate.now().getMonth().toString() + " " + java.time.LocalDate.now().getYear());
            addResult("Base Monthly Salary", formatINR(baseSalary));
            addResult("Per Day Salary (Base/30)", formatINR(perDaySalary));
            addResult("Leave Days Taken", String.valueOf(leaveDays));
            addResult("Leave Deduction", formatINR(leaveDeduction));
            addResult("5th Monday Bonus", formatINR(fifthMondayBonus));
            addResult("Salary with Bonus", formatINR(salaryWithBonus));
            addResult("Tax Deduction", formatINR(tax));
            addResult("Medical Deduction", formatINR(medical));
            addResult("PF Deduction (10%)", formatINR(pfDeduction));
            addResult("Dabba Kada Deduction", formatINR(dabbaDeduction));
            addResult("Total Deductions", formatINR(totalDeductions));
            addResult("Net Monthly Salary", formatINR(netMonthly));

        } catch (Exception e) {
            Toast.makeText(this, "Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    private void addResult(String title, String value) {
        TextView tv = new TextView(this);
        tv.setText(title + ": " + value);
        tv.setTextSize(16);
        resultsBox.addView(tv);
    }

    private boolean hasFifthMonday() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        cal.set(year, month, 1);

        int mondayCount = 0;
        while (cal.get(Calendar.MONTH) == month) {
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                mondayCount++;
            }
            cal.add(Calendar.DATE, 1);
        }
        return mondayCount >= 5;
    }

    private String formatINR(double amount) {
        // Basic Indian grouping
        java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        java.text.DecimalFormat formatter = new java.text.DecimalFormat("##,##,##0.00", symbols);
        return "₹" + formatter.format(amount);
    }

    private double parseDouble(String s) {
        if (s == null || s.trim().isEmpty()) return 0.0;
        return Double.parseDouble(s.trim());
    }

    private int parseInt(String s) {
        if (s == null || s.trim().isEmpty()) return 0;
        return Integer.parseInt(s.trim());
    }
}
