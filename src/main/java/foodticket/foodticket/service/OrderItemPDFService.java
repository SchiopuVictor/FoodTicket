package foodticket.foodticket.service;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import foodticket.foodticket.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OrderItemPDFService {
    private static Table createDashedBorder() {
        Table line = new Table(1).useAllAvailableWidth().setBorder(Border.NO_BORDER);
        Cell pointedLine = new Cell();
        pointedLine.setBorderTop(new com.itextpdf.layout.borders.DashedBorder(ColorConstants.GRAY, 0.2f));
        pointedLine.setBorder(Border.NO_BORDER);
        line.addCell(pointedLine);
        return line;
    }

    public byte[] createPdf(OrderItem item) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);

        PdfDocument pdfDocument = new PdfDocument(writer);
        PageSize size = new PageSize(130, 190);
        Document document = new Document(pdfDocument, size);

        Table pricipaleTable = new Table(1).useAllAvailableWidth();

        Cell cell = new Cell();
        cell.add(new Paragraph("Bon Fiscal").setFontSize(7));
        cell.setBorder(Border.NO_BORDER);
        cell.setTextAlignment(TextAlignment.CENTER);
        cell.setPaddingTop(-25);
        pricipaleTable.addCell(cell);

        Table secondTable = new Table(1).useAllAvailableWidth();
        Cell secondCell = new Cell();

        secondCell.setBorder(Border.NO_BORDER);
        secondCell.setPaddingTop(-15);
        secondCell.add(new Paragraph(
                "StarKebab Kebabarie Company SRL \n" +
                        "STR.Columna 60, Sector: Centru\n" +
                        "Cod Fiscal: " + item.getId()).setFontSize(3));

        secondTable.addCell(secondCell);

        float[] tableMeasure = {2.5f, 2.5f};

        Table midTable = new Table(UnitValue.createPointArray(tableMeasure)).useAllAvailableWidth();
        Cell leftMid = new Cell();
        leftMid.setBorder(Border.NO_BORDER);
        leftMid.add(new Paragraph("Numar Bon: " + item.getId()).setFontSize(3));
        leftMid.add(new Paragraph(item.getProduct().getName() + "\tx" + item.getQuantity()).setFontSize(3));


        Cell rightMid = new Cell();
        rightMid.setTextAlignment(TextAlignment.RIGHT);
        rightMid.setBorder(Border.NO_BORDER);
        rightMid.add(new Paragraph(" "));
        rightMid.add(new Paragraph("\n" + item.getProduct().getPrice().toString()).setFontSize(3));

        midTable.addCell(leftMid);
        midTable.addCell(rightMid);

        Table line = createDashedBorder();

        Table totalTable = new Table(tableMeasure).useAllAvailableWidth();

        Cell leftTotal = new Cell();
        leftTotal.setBorder(Border.NO_BORDER);
        leftTotal.setPaddingTop(-2);
        leftTotal.add(new Paragraph("Total: ").setFontSize(4).setBold());
        leftTotal.add(new Paragraph("CASH: ").setFontSize(3));
        leftTotal.add(new Paragraph("Rest:").setFontSize(3));


        Cell rightTotal = new Cell();
        rightTotal.setPaddingTop(-2);
        rightTotal.setBorder(Border.NO_BORDER);
        rightTotal.setTextAlignment(TextAlignment.RIGHT);
        rightTotal.add(new Paragraph(item.getOrder().getTotalAmount().toString()).setFontSize(4));
        rightTotal.add(new Paragraph(item.getOrder().getTotalAmount().toString()).setFontSize(3));
        rightTotal.add(new Paragraph("0.00").setFontSize(3));

        totalTable.addCell(leftTotal);
        totalTable.addCell(rightTotal);

        Table infoTable = new Table(1).useAllAvailableWidth();

        Cell infoCell = new Cell();
        infoCell.setPaddingTop(-2);
        infoCell.setBorder(Border.NO_BORDER);
        infoCell.add(new Paragraph("Nume: " + item.getOrder().getCashierName()).setFontSize(3));
        infoCell.add(new Paragraph("Casa: 1").setFontSize(3));

        infoTable.addCell(infoCell);

        Table footer = new Table(tableMeasure).useAllAvailableWidth();
        Cell leftFooter = new Cell();
        leftFooter.setBorder(Border.NO_BORDER);
        leftFooter.setPaddingTop(-3);
        leftFooter.add(new Paragraph("Total TVA: ").setFontSize(3));
        leftFooter.add(new Paragraph("Cota TVA: ").setFontSize(3));
        leftFooter.add(new Paragraph("Data: ").setFontSize(3));
        leftFooter.add(new Paragraph("Metoda de Plata: ").setFontSize(3));
        leftFooter.add(new Paragraph(item.getOrder().getStatus().toString()).setFontSize(3).setTextAlignment(TextAlignment.CENTER));

        Cell rightFooter = new Cell();
        rightFooter.setBorder(Border.NO_BORDER);
        rightFooter.setPaddingTop(-3);
        rightFooter.setTextAlignment(TextAlignment.RIGHT);
        rightFooter.add(new Paragraph("3.56").setFontSize(3));
        rightFooter.add(new Paragraph("24.00%").setFontSize(3));
        rightFooter.add(new Paragraph(item.getOrder().getDateTime().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))).setFontSize(3));
        rightFooter.add(new Paragraph(item.getOrder().getPaymentMethod().toString()).setFontSize(3));

        footer.addCell(leftFooter);
        footer.addCell(rightFooter);

        document.add(pricipaleTable);
        document.add(secondTable);
        document.add(midTable);
        document.add(line);
        document.add(totalTable);
        document.add(line);
        document.add(infoTable);
        document.add(line);
        document.add(footer);

        document.close();

        return out.toByteArray();

    }

}
