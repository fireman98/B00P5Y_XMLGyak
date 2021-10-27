package dom_demo;
import com.sun.org.apache.xerces.internal.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import oracle.jdbc.driver.*; 
import org.xml.sax.*;
public class sax_jdbc {
    Connection kap;
    public sax_jdbc() {
    }
    public static void main(String[] args) {
        sax_jdbc prog = new sax_jdbc();
        prog.feldolgoz();
    }

    public void feldolgoz() {
        XMLReader xmlr = new SAXParser();
        Elemzo sch = new Elemzo();
        xmlr.setContentHandler(sch);

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            kap =
                DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "u1", "u1");
            System.out.println("connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            xmlr.parse("C:\\KOVI\\OKTATAS\\xml_bsc\\oo2.xml");
        } catch (IOException e) {
            System.out.println("IO hiba: " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("SAX hiba");
        }
    }

    class Elemzo implements ContentHandler {
        int te,se, le;
        String tv,sv, lv;

        public void setDocumentLocator(Locator locator) {
        }

        public void startDocument() {
        }

        public void endDocument() {
        }

        public void startPrefixMapping(String prefix, String uri) {
        }

        public void endPrefixMapping(String prefix) {
        }

        public void startElement(String uri, String localName, String qName,
        Attributes atts) {
            if (localName.compareTo("tipus") == 0) {
                te = 1;
            }

            if (localName.compareTo("szint") == 0) {
                 se = 1;
             }

            if (localName.compareTo("leiras") == 0) {
                le = 1;
            }
        }

        public void endElement(String uri, String localName, String qName) {
             if (localName.compareTo("tipus") == 0) {
                te = 0;
              }
             if (localName.compareTo("szint") == 0) {
                se = 0;
             }
        if (localName.compareTo("leiras") == 0) {
            le = 0;
            if (Integer.parseInt(sv) > 2) {
                if (tv.compareTo("hiba") == 0) {
                    String sql = "INSERT INTO HIBAUZENETEK VALUES(" + sv + ",SYSDATE,'"
                        + lv + "')";
                    System.out.println(sql);
                    try {
                        Statement pp = kap.createStatement();
                        pp.executeUpdate(sql);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
    public void characters(char[] ch, int start, int length) {
        //System.out.println(String.valueOf(start)+":"+String.valueOf(length));
        String t = new String(ch);
        String sz = t.substring(start, start + length);
        if (te == 1) { tv = sz; }
        if (se == 1) { sv = sz; }
        if (le == 1) { lv = sz; }

    }

    public void ignorableWhitespace(char[] ch, int start, int length) {
    }

    public void processingInstruction(String target, String data) {
    }

    public void skippedEntity(String name) {
    }

    }
}
