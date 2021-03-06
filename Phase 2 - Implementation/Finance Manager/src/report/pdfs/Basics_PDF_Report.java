package report.pdfs;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;
import java.util.TimeZone;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import financemanager.datahandler.*;
import financemanager.sqlhandler.SQL_Handler;
import report.barcharts.Basics_Bar_Chart_Report;
import report.piecharts.Basics_Pie_Chart_Report;

public class Basics_PDF_Report {
    SQL_Handler sql = new SQL_Handler();

    String end = getDate() + " " + getTime();
    private String FILE = "Reports/Basic Reports/Basic Overview Reports/" + end + ".pdf";
    private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    java.util.List<Fertilizer_Data> fData;
    java.util.List<Gas_Data> gData;
    java.util.List<Labour_Data> lData;
    java.util.List<Lighting_Data> liData;
    java.util.List<Machinery_Data> maData;
    java.util.List<Mortgaged_Data> mData;
    java.util.List<Rented_Data> rData;
    java.util.List<Slurry_Data> sData;
    java.util.List<Water_Data> wData;

    boolean show = false;

    public Basics_PDF_Report() {
    	try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            document.close();
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private void addMetaData(Document document) {
    	document.addTitle("Basics Overview Report");
	document.addSubject("Bascis");
	document.addKeywords("Basics, Report");
	document.addAuthor("admin");
	document.addCreator(System.getProperty("user.name"));
    }
    private void addTitlePage(Document document) throws DocumentException {
	Paragraph preface = new Paragraph();
        // We add one empty line
	addEmptyLine(preface, 1);
        // Lets write a big header
	preface.add(new Paragraph("Basics Overview", catFont));

	addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
	preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") +
            ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            smallBold));
	addEmptyLine(preface, 3);
	preface.add(new Paragraph("This document describes something which is very important ",
            smallBold));

	addEmptyLine(preface, 8);

	preface.add(new Paragraph("This document is a preliminary version and not subject to "
            + "your license agreement or any other agreement with vogella.com ;-).",
            redFont));

	document.add(preface);
        // Start a new page
	document.newPage();
    }
    private void addContent(Document document) throws DocumentException {
	Anchor anchor = new Anchor("Table Report", catFont);
	anchor.setName("Table Report");

        // Second parameter is the number of the chapter
	Chapter catPart = new Chapter(new Paragraph(anchor), 1);

	Paragraph paragraph = new Paragraph();

        // Add a table
	document.add(new Paragraph());
	createTable(catPart);

        // Now add all this to the document
	document.add(catPart);

        // Start a new page
	document.newPage();

        // Next section
	anchor = new Anchor("Second Chapter", catFont);
	anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
	catPart = new Chapter(new Paragraph(anchor), 2);

        // Add an image
	addEmptyLine(paragraph, 5);
	addBarChart(catPart);

        // Now add all this to the document
	document.add(catPart);

        // Start a new page
	document.newPage();

        // Next section
	anchor = new Anchor("Third Chapter", catFont);
	anchor.setName("Third Chapter");

        // Second parameter is the number of the chapter
	catPart = new Chapter(new Paragraph(anchor), 3);

        // Add an image
	addEmptyLine(paragraph, 5);
	addPieChart(catPart);

        // Now add all this to the document
	document.add(catPart);
    }
    private void createTable(Section catPart) throws BadElementException {
	PdfPTable table = new PdfPTable(4);
        table.setSpacingBefore(25);
        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

	PdfPCell c1 = new PdfPCell(new Phrase("Type"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);

	c1 = new PdfPCell(new Phrase("Expense"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);

	c1 = new PdfPCell(new Phrase("Month"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Year"));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);

	table.setHeaderRows(1);

	selectAll();
        calcFExpense(table);
        calcGExpense(table);
        calcLExpense(table);
        calcLiExpense(table);
        calcMaExpense(table);
        calcMExpense(table);
        calcRExpense(table);
        calcSExpense(table);
        calcWExpense(table);

	catPart.add(table);
    }
    private void addEmptyLine(Paragraph paragraph, int number) {
	for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
	}
    }
    private void addBarChart(Section catPart) {
	Basics_Bar_Chart_Report chart = new Basics_Bar_Chart_Report(show, "Basic Expenses Overview");
        Image To_be_Added = null;
	try {
            To_be_Added = Image.getInstance("bar_chart.png");
	} catch (BadElementException e) {
            e.printStackTrace();
	} catch (MalformedURLException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
	}

	To_be_Added.setAlignment(Image.MIDDLE | Image.TEXTWRAP);
	//To_be_Added.setBorder(Image.BOX);
	//To_be_Added.setBorderWidth(15);

	catPart.add(To_be_Added);
    }
    private void addPieChart(Section catPart) {
	Basics_Pie_Chart_Report chart = new Basics_Pie_Chart_Report(show, "Comparison", "Basic Expenses Overview");
        Image To_be_Added = null;
	try {
            To_be_Added = Image.getInstance("pie_chart.png");
	} catch (BadElementException e) {
            e.printStackTrace();
	} catch (MalformedURLException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
	}

	To_be_Added.setAlignment(Image.MIDDLE | Image.TEXTWRAP);
	//To_be_Added.setBorder(Image.BOX);
	//To_be_Added.setBorderWidth(15);

	catPart.add(To_be_Added);
    }

    private final static String getDate()   {
	DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" ) ;
	return (df.format(new Date())) ;
    }
    private final static String getTime()   {
	DateFormat df = new SimpleDateFormat("hh-mm-ss") ;
	df.setTimeZone(TimeZone.getTimeZone("Ireland")) ;

	return (df.format(new Date())) ;
    }

    private void selectAll() {
        try {
            fData = sql.select_fertilizer_expense();
            gData = sql.select_gas_expense();
            lData = sql.select_labour_expense();
            liData = sql.select_lighting_expense();
            maData = sql.select_machinery_expense();
            mData = sql.select_mortgaged_land();
            rData = sql.select_rented_land();
            sData = sql.select_slurry_expense();
            wData = sql.select_water_expense();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void calcFExpense(PdfPTable table) {
        ListIterator<Fertilizer_Data> data_list = fData.listIterator();
        //populating the tablemodel
        table.addCell("Fertilizer");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        while(data_list.hasNext()) {
            Fertilizer_Data d = data_list.next();
            table.addCell("");
            table.addCell("" + d.getExpense());
            table.addCell(d.getMonth());
            table.addCell("" + d.getYear());
        }
    }
    private void calcGExpense(PdfPTable table) {
        ListIterator<Gas_Data> data_list = gData.listIterator();
        //populating the tablemodel
        table.addCell("Gas");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        while(data_list.hasNext()) {
            Gas_Data d = data_list.next();
            table.addCell("");
            table.addCell("" + d.getExpense());
            table.addCell(d.getMonth());
            table.addCell("" + d.getYear());
        }
    }
    private void calcLExpense(PdfPTable table) {
        ListIterator<Labour_Data> data_list = lData.listIterator();
        //populating the tablemodel
        table.addCell("Labour");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        while(data_list.hasNext()) {
            Labour_Data d = data_list.next();
            table.addCell("");
            table.addCell("" + d.getExpense());
            table.addCell(d.getMonth());
            table.addCell("" + d.getYear());
        }
    }
    private void calcLiExpense(PdfPTable table) {
        ListIterator<Lighting_Data> data_list = liData.listIterator();
        //populating the tablemodel
        table.addCell("Lighting");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        while(data_list.hasNext()) {
            Lighting_Data d = data_list.next();
            table.addCell("");
            table.addCell("" + d.getExpense());
            table.addCell(d.getMonth());
            table.addCell("" + d.getYear());
        }
    }
    private void calcMaExpense(PdfPTable table) {
        ListIterator<Machinery_Data> data_list = maData.listIterator();
        //populating the tablemodel
        table.addCell("Machinery");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        while(data_list.hasNext()) {
            Machinery_Data d = data_list.next();
            table.addCell("");
            table.addCell("" + d.getExpense());
            table.addCell(d.getMonth());
            table.addCell("" + d.getYear());
        }
    }
    private void calcMExpense(PdfPTable table) {
        ListIterator<Mortgaged_Data> data_list = mData.listIterator();
        //populating the tablemodel
        table.addCell("Mortgaged");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        while(data_list.hasNext()) {
            Mortgaged_Data d = data_list.next();
            table.addCell("");
            table.addCell("" + d.getExpense());
            table.addCell(d.getMonth());
            table.addCell("" + d.getYear());
        }
    }
    private void calcRExpense(PdfPTable table) {
        ListIterator<Rented_Data> data_list = rData.listIterator();
        //populating the tablemodel
        table.addCell("Rented");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        while(data_list.hasNext()) {
            Rented_Data d = data_list.next();
            table.addCell("");
            table.addCell("" + d.getExpense());
            table.addCell(d.getMonth());
            table.addCell("" + d.getYear());
        }
    }
    private void calcSExpense(PdfPTable table) {
        ListIterator<Slurry_Data> data_list = sData.listIterator();
        //populating the tablemodel
        table.addCell("Slurry");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        while(data_list.hasNext()) {
            Slurry_Data d = data_list.next();
            table.addCell("");
            table.addCell("" + d.getExpense());
            table.addCell(d.getMonth());
            table.addCell("" + d.getYear());
        }
    }
    private void calcWExpense(PdfPTable table) {
        ListIterator<Water_Data> data_list = wData.listIterator();
        //populating the tablemodel
        table.addCell("Water");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        while(data_list.hasNext()) {
            Water_Data d = data_list.next();
            table.addCell("");
            table.addCell("" + d.getExpense());
            table.addCell(d.getMonth());
            table.addCell("" + d.getYear());
        }
    }
}