package com.example.finance.resource;

import com.example.finance.model.ExpenseModel;
import com.example.finance.model.IncomeModel;
import com.example.finance.repositary.ExpenseRepository;
import com.example.finance.repositary.IncomeRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping("/generate")
    public String generateReport(
            @RequestParam("startMonth") String startMonth,
            @RequestParam("endMonth") String endMonth,
            Model model) {

        YearMonth start = YearMonth.parse(startMonth);
        YearMonth end = YearMonth.parse(endMonth);

        LocalDate startDate = start.atDay(1);
        LocalDate endDate = end.atEndOfMonth();

        List<IncomeModel> incomeList =
                incomeRepository.getIncomeReport(startDate, endDate);

        List<ExpenseModel> expenseList =
                expenseRepository.getExpenseReport(startDate, endDate);

        double totalIncome = incomeList.stream()
                .mapToDouble(IncomeModel::getAmount)
                .sum();

        double totalExpense = expenseList.stream()
                .mapToDouble(ExpenseModel::getAmount)
                .sum();

        model.addAttribute("incomeList", incomeList);
        model.addAttribute("expenseList", expenseList);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("balance", totalIncome - totalExpense);
        model.addAttribute("startMonth", startMonth);
        model.addAttribute("endMonth", endMonth);

        return "ReportResult";
    }
    @RequestMapping("/pdf")
    public void downloadPdf(HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=FinanceReport.pdf");

        Document document = new Document(PageSize.A4, 40, 40, 40, 40);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22);
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 11);

        Paragraph title = new Paragraph("FINANCE MANAGEMENT SYSTEM", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph report = new Paragraph("Income & Expense Report", headingFont);
        report.setAlignment(Element.ALIGN_CENTER);
        document.add(report);

        document.add(new Paragraph("Generated On : " + LocalDate.now(), normalFont));
        document.add(new Paragraph(" "));

        List<IncomeModel> incomeList = incomeRepository.findAll();
        List<ExpenseModel> expenseList = expenseRepository.findAll();

        double totalIncome = incomeList.stream()
                .mapToDouble(IncomeModel::getAmount)
                .sum();

        double totalExpense = expenseList.stream()
                .mapToDouble(ExpenseModel::getAmount)
                .sum();

        double balance = totalIncome - totalExpense;

        PdfPTable summary = new PdfPTable(3);
        summary.setWidthPercentage(100);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Total Income", headingFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        summary.addCell(cell);

        cell = new PdfPCell(new Phrase("Total Expense", headingFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        summary.addCell(cell);

        cell = new PdfPCell(new Phrase("Balance", headingFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        summary.addCell(cell);

        summary.addCell("Rs. " + totalIncome);
        summary.addCell("Rs. " + totalExpense);
        summary.addCell("Rs. " + balance);

        document.add(summary);

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Income Records", headingFont));
        document.add(new Paragraph(" "));

        PdfPTable incomeTable = new PdfPTable(3);
        incomeTable.setWidthPercentage(100);

        incomeTable.addCell("Source");
        incomeTable.addCell("Amount");
        incomeTable.addCell("Date");

        for (IncomeModel income : incomeList) {
            incomeTable.addCell(income.getSource());
            incomeTable.addCell(String.valueOf(income.getAmount()));
            incomeTable.addCell(String.valueOf(income.getDate()));
        }

        document.add(incomeTable);

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Expense Records", headingFont));
        document.add(new Paragraph(" "));

        PdfPTable expenseTable = new PdfPTable(3);
        expenseTable.setWidthPercentage(100);

        expenseTable.addCell("Description");
        expenseTable.addCell("Amount");
        expenseTable.addCell("Date");

        for (ExpenseModel expense : expenseList) {
            expenseTable.addCell(expense.getDescription());
            expenseTable.addCell(String.valueOf(expense.getAmount()));
            expenseTable.addCell(String.valueOf(expense.getDate()));
        }

        document.add(expenseTable);

        document.add(new Paragraph(" "));
        Paragraph footer = new Paragraph(
                "Generated by Finance Management System",
                normalFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);

        document.close();
    }
}