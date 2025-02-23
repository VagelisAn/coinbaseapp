
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

export class PdfUtil {
  static exportToPdf(title: string, headers: string[], data: any[], fileName: string = 'report.pdf') {
    const doc = new jsPDF();

    
    doc.setFontSize(18);
    doc.text(title, 14, 15);

    
    autoTable(doc, {
      head: [headers], 
      body: data, 
      startY: 25,
    });

    
    doc.save(fileName);
  }
}