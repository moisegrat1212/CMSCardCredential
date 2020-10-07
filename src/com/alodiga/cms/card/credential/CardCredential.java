/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alodiga.cms.card.credential;

import com.alodiga.cms.json.card.AssignPhysicalCardResponse;
import com.alodiga.cms.json.card.AssignVirtualCardResponse;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author moise
 */
public class CardCredential {
    //1)assignVirtualCard()
    //2)assignPhysicalCard()

    public static void main(String[] args) throws Exception {
        CardCredential cardCredential = new CardCredential();
        cardCredential.assignPhysicalCard();
    }

    public AssignVirtualCardResponse assignVirtualCard() throws MalformedURLException, IOException, Exception {

        HttpURLConnection connection = null;
        InputStream is = null;
        String response = null;
        String formattedSOAPResponse = "";
        String responseString = "";
        String outputString = "";
        try {
            // set up URL connection
            java.net.URL url = new java.net.URL("https://master.credencial.com.ar/cgi-bin/nwwcgitst/PRUWEB/CWWS005B");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // write out form parameters
            String postParamaters = "&OPS=RegAlw&COD=758&PTR=484&TDC=CC&NDC=0205C000000003&FAL=20201005&FNC=19680204&DCT=SMITH+%20944&DBT=H&DLT=Benito%20Juarez&CPN=03230&NTC=525585258744&MCE=NANCY.RAMELLA@CREDENCIAL.COM.MX&CST=F&APS=RAMELLA&NBS=NANCY&CUI=VACS691110UY9&PRO=Q758&AFF=0002&MGT=N&ICEL=95000,00&DCTE=Cerrito%20740&DPTE=H&DLTE=Benito%20Juárez&CPNE=03230&CPLE=10&PENC=IVJVG1GEJeY%2B8sthmXXWo50vwEC%2B6MsK/plOBEtjZoRXLhvg8atTGL6PdlSnS59PEiWmb2/MGIqgdetPUzydTjErk25adtoTnRGLajn2iYjuc9rPVrsFXJNAxcQEF9BUv4YrD4FY%2BHdUlBjWorDDMuH5Jtsoazg0kVLZ6BaawXA=";
            //System.out.println(postParamaters);
            connection.setFixedLengthStreamingMode(postParamaters.getBytes().length);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(postParamaters);
            out.close();
            //Get Response  
            try {
                is = connection.getInputStream();
            } catch (IOException ioe) {
                if (connection instanceof HttpURLConnection) {
                    HttpURLConnection httpConn = (HttpURLConnection) connection;
                    int statusCode = httpConn.getResponseCode();
                    System.out.println(httpConn.getResponseCode());
                    System.out.println(statusCode);
                    if (statusCode != 200) {
                        is = httpConn.getErrorStream();
                    }
                }
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            //StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            //String line;
            while ((responseString = rd.readLine()) != null) {
//                response.append(line);
//                response.append('\r');
                //response = line;
                outputString = outputString + responseString;
                //System.out.println(outputString);
            }
            Document doc = parseXmlFile(outputString);
            NodeList nodesResponse = doc.getElementsByTagName("Response");
            Element elementResponse = (Element) nodesResponse.item(0);
            NodeList codigo = elementResponse.getElementsByTagName("Codigo");
            Element Codigo = (Element) codigo.item(0);
            NodeList timeStamp = elementResponse.getElementsByTagName("TimeStamp");
            Element TimeStamp = (Element) timeStamp.item(0);
            NodeList codigoRespuesta = elementResponse.getElementsByTagName("CodigoRespuesta");
            Element CodigoRespuesta = (Element) codigoRespuesta.item(0);
            NodeList errorCodigo = elementResponse.getElementsByTagName("ErrorCodigo");
            Element ErrorCodigo = (Element) errorCodigo.item(0);
            NodeList errorDescripcion = elementResponse.getElementsByTagName("ErrorDescripcion");
            Element ErrorDescripcion = (Element) errorDescripcion.item(0);
            NodeList nodesRegAlta = doc.getElementsByTagName("RegAlta");
            Element elementRegAlta = (Element) nodesRegAlta.item(0);
            NodeList pais = elementRegAlta.getElementsByTagName("Pais");
            Element Pais = (Element) pais.item(0);
            NodeList tDoc = elementRegAlta.getElementsByTagName("TDoc");
            Element TDoc = (Element) tDoc.item(0);
            NodeList nDoc = elementRegAlta.getElementsByTagName("NDoc");
            Element NDoc = (Element) nDoc.item(0);
            NodeList fAlta = elementRegAlta.getElementsByTagName("FAlta");
            Element FAlta = (Element) fAlta.item(0);
            NodeList fNac = elementRegAlta.getElementsByTagName("FNac");
            Element FNac = (Element) fNac.item(0);
            NodeList denC = elementRegAlta.getElementsByTagName("DenC");
            Element DenC = (Element) denC.item(0);
            NodeList denB = elementRegAlta.getElementsByTagName("DenB");
            Element DenB = (Element) denB.item(0);
            NodeList denL = elementRegAlta.getElementsByTagName("DenL");
            Element DenL = (Element) denL.item(0);
            NodeList codP = elementRegAlta.getElementsByTagName("CodP");
            Element CodP = (Element) codP.item(0);
            NodeList nTel = elementRegAlta.getElementsByTagName("NTel");
            Element NTel = (Element) nTel.item(0);
            NodeList mail = elementRegAlta.getElementsByTagName("Mail");
            Element Mail = (Element) mail.item(0);
            NodeList sexo = elementRegAlta.getElementsByTagName("Sexo");
            Element Sexo = (Element) sexo.item(0);
            NodeList apN = elementRegAlta.getElementsByTagName("ApN");
            Element ApN = (Element) apN.item(0);
            NodeList nmB = elementRegAlta.getElementsByTagName("NmB");
            Element NmB = (Element) nmB.item(0);
            NodeList cui = elementRegAlta.getElementsByTagName("Cui");
            Element Cui = (Element) cui.item(0);
            NodeList pro = elementRegAlta.getElementsByTagName("Pro");
            Element Pro = (Element) pro.item(0);
            NodeList aff = elementRegAlta.getElementsByTagName("Aff");
            Element Aff = (Element) aff.item(0);
            NodeList graTar = elementRegAlta.getElementsByTagName("GraTar");
            Element GraTar = (Element) graTar.item(0);
            NodeList vnd = elementRegAlta.getElementsByTagName("Vnd");
            Element Vnd = (Element) vnd.item(0);
            NodeList imp = elementRegAlta.getElementsByTagName("Imp");
            Element Imp = (Element) imp.item(0);
            NodeList alias = elementRegAlta.getElementsByTagName("Alias");
            Element Alias = (Element) alias.item(0);
            NodeList ctasig = elementRegAlta.getElementsByTagName("Ctasig");
            Element Ctasig = (Element) ctasig.item(0);
            NodeList clabe = elementRegAlta.getElementsByTagName("Clabe");
            Element Clabe = (Element) clabe.item(0);
            NodeList callent = elementRegAlta.getElementsByTagName("Callent");
            Element Callent = (Element) callent.item(0);
            NodeList localent = elementRegAlta.getElementsByTagName("Localent");
            Element Localent = (Element) localent.item(0);
            NodeList cPosent = elementRegAlta.getElementsByTagName("CPosent");
            Element CPosent = (Element) cPosent.item(0);
            NodeList plodent = elementRegAlta.getElementsByTagName("Plodent");
            Element Plodent = (Element) plodent.item(0);
            NodeList provent = elementRegAlta.getElementsByTagName("Provent");
            Element Provent = (Element) provent.item(0);

            AssignVirtualCardResponse assignVirtualCardResponse = new AssignVirtualCardResponse();
            try {
                assignVirtualCardResponse.setCodigo(getCharacterDataFromElement(Codigo));
                assignVirtualCardResponse.setTimeStamp(getCharacterDataFromElement(TimeStamp));
                assignVirtualCardResponse.setCodigoRespuesta(getCharacterDataFromElement(CodigoRespuesta));
                assignVirtualCardResponse.setErrorCodigo(getCharacterDataFromElement(ErrorCodigo));
                assignVirtualCardResponse.setErrorDescripcion(getCharacterDataFromElement(ErrorDescripcion));
                assignVirtualCardResponse.setPais(getCharacterDataFromElement(Pais));
                assignVirtualCardResponse.setTDoc(getCharacterDataFromElement(TDoc));
                assignVirtualCardResponse.setNDoc(getCharacterDataFromElement(NDoc));
                assignVirtualCardResponse.setFAlta(getCharacterDataFromElement(FAlta));
                assignVirtualCardResponse.setFNac(getCharacterDataFromElement(FNac));
                assignVirtualCardResponse.setDenC(getCharacterDataFromElement(DenC));
                assignVirtualCardResponse.setDenB(getCharacterDataFromElement(DenB));
                assignVirtualCardResponse.setDenL(getCharacterDataFromElement(DenL));
                assignVirtualCardResponse.setCodP(getCharacterDataFromElement(CodP));
                assignVirtualCardResponse.setNTel(getCharacterDataFromElement(NTel));
                assignVirtualCardResponse.setMail(getCharacterDataFromElement(Mail));
                assignVirtualCardResponse.setSexo(getCharacterDataFromElement(Sexo));
                assignVirtualCardResponse.setApN(getCharacterDataFromElement(ApN));
                assignVirtualCardResponse.setNmB(getCharacterDataFromElement(NmB));
                assignVirtualCardResponse.setCui(getCharacterDataFromElement(Cui));
                assignVirtualCardResponse.setPro(getCharacterDataFromElement(Pro));
                assignVirtualCardResponse.setAff(getCharacterDataFromElement(Aff));
                assignVirtualCardResponse.setGraTar(getCharacterDataFromElement(GraTar));
                assignVirtualCardResponse.setVnd(getCharacterDataFromElement(Vnd));
                assignVirtualCardResponse.setImp(getCharacterDataFromElement(Imp));
                assignVirtualCardResponse.setAlias(getCharacterDataFromElement(Alias));
                assignVirtualCardResponse.setCtasig(getCharacterDataFromElement(Ctasig));
                assignVirtualCardResponse.setClabe(getCharacterDataFromElement(Clabe));
                assignVirtualCardResponse.setCallent(getCharacterDataFromElement(Callent));
                assignVirtualCardResponse.setLocalent(getCharacterDataFromElement(Localent));
                assignVirtualCardResponse.setCPosent(getCharacterDataFromElement(CPosent));
                assignVirtualCardResponse.setPlodent(getCharacterDataFromElement(Plodent));
                assignVirtualCardResponse.setProvent(getCharacterDataFromElement(Provent));

            } catch (Exception e) {
                e.printStackTrace();
            }
            rd.close();
            return new AssignVirtualCardResponse(assignVirtualCardResponse.getCodigo(), assignVirtualCardResponse.getTimeStamp(), assignVirtualCardResponse.getCodigoRespuesta(), assignVirtualCardResponse.getErrorCodigo(), assignVirtualCardResponse.getErrorDescripcion(), assignVirtualCardResponse.getPais(), assignVirtualCardResponse.getTDoc(), assignVirtualCardResponse.getNDoc(), assignVirtualCardResponse.getFAlta(), assignVirtualCardResponse.getFNac(), assignVirtualCardResponse.getDenC(), assignVirtualCardResponse.getDenB(), assignVirtualCardResponse.getDenL(), assignVirtualCardResponse.getCodP(), assignVirtualCardResponse.getNTel(), assignVirtualCardResponse.getMail(), assignVirtualCardResponse.getSexo(), assignVirtualCardResponse.getApN(), assignVirtualCardResponse.getNmB(), assignVirtualCardResponse.getCui(), assignVirtualCardResponse.getPro(), assignVirtualCardResponse.getAff(), assignVirtualCardResponse.getGraTar(), assignVirtualCardResponse.getVnd(), assignVirtualCardResponse.getImp(), assignVirtualCardResponse.getAlias(), assignVirtualCardResponse.getCtasig(), assignVirtualCardResponse.getClabe(), assignVirtualCardResponse.getCallent(), assignVirtualCardResponse.getLocalent(), assignVirtualCardResponse.getCPosent(), assignVirtualCardResponse.getPlodent(), assignVirtualCardResponse.getProvent());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new MalformedURLException();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public AssignPhysicalCardResponse assignPhysicalCard() throws MalformedURLException, IOException, Exception {

        HttpURLConnection connection = null;
        InputStream is = null;
        String response = null;
        String formattedSOAPResponse = "";
        String responseString = "";
        String outputString = "";
        try {
            // set up URL connection
            java.net.URL url = new java.net.URL("https://master.credencial.com.ar/cgi-bin/nwwcgitst/PRUWEB/CWWS005B");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // write out form parameters
            String postParamaters = "OPS=RegMod&COD=758&PTR=484&ALANT=5255852587440055&ALACT=525812584475&DCTE=Cerrito%20740&DPTE=H&DLTE=Benito%20Ju%C3%A1rez&CPNE=03230&CPLE=10&MGT=S&MCE=NANCY.RAMELLA@CREDENCIAL.COM.MX&PENC=eYIryr64lLE%2BClVzaF3SfwpZRT5CYtPKylFHrMZASQg/EyTlFf0JUapkb0T7kBB7sqxiVwmY%2BlCeErg9vo7JAsGnzFPPbf6KKpzLb5uLZgjBuCUkAc6N8BNn/wXFa4g5KVX/KI90ghuk0RErJw/K23tNh2KSE0svUOdEm0elYCM=";
            //System.out.println(postParamaters);
            connection.setFixedLengthStreamingMode(postParamaters.getBytes().length);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(postParamaters);
            out.close();
            //Get Response  
            try {
                is = connection.getInputStream();
            } catch (IOException ioe) {
                if (connection instanceof HttpURLConnection) {
                    HttpURLConnection httpConn = (HttpURLConnection) connection;
                    int statusCode = httpConn.getResponseCode();
                    System.out.println(httpConn.getResponseCode());
                    System.out.println(statusCode);
                    if (statusCode != 200) {
                        is = httpConn.getErrorStream();
                    }
                }
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            //StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            //String line;
            while ((responseString = rd.readLine()) != null) {
//                response.append(line);
//                response.append('\r');
                //response = line;
                outputString = outputString + responseString;
                //System.out.println(outputString);
            }

            Document doc = parseXmlFile(outputString);
            NodeList nodesResponse = doc.getElementsByTagName("Response");
            Element elementResponse = (Element) nodesResponse.item(0);
            NodeList codigo = elementResponse.getElementsByTagName("Codigo");
            Element Codigo = (Element) codigo.item(0);
            NodeList timeStamp = elementResponse.getElementsByTagName("TimeStamp");
            Element TimeStamp = (Element) timeStamp.item(0);
            NodeList codigoRespuesta = elementResponse.getElementsByTagName("CodigoRespuesta");
            Element CodigoRespuesta = (Element) codigoRespuesta.item(0);
            NodeList errorCodigo = elementResponse.getElementsByTagName("ErrorCodigo");
            Element ErrorCodigo = (Element) errorCodigo.item(0);
            NodeList errorDescripcion = elementResponse.getElementsByTagName("ErrorDescripcion");
            Element ErrorDescripcion = (Element) errorDescripcion.item(0);
            NodeList nodesRegAlta = doc.getElementsByTagName("RegMod");
            Element elementRegAlta = (Element) nodesRegAlta.item(0);
            NodeList pais = elementRegAlta.getElementsByTagName("Pais");
            Element Pais = (Element) pais.item(0);
            NodeList ruc = elementRegAlta.getElementsByTagName("Ruc");
            Element Ruc = (Element) ruc.item(0);
            NodeList alant = elementRegAlta.getElementsByTagName("Alant");
            Element Alant = (Element) alant.item(0);
            NodeList alact = elementRegAlta.getElementsByTagName("Alact");
            Element Alact = (Element) alact.item(0);
            NodeList callent = elementRegAlta.getElementsByTagName("Callent");
            Element Callent = (Element) callent.item(0);
            NodeList localent = elementRegAlta.getElementsByTagName("Localent");
            Element Localent = (Element) localent.item(0);
            NodeList cPosent = elementRegAlta.getElementsByTagName("CPosent");
            Element CPosent = (Element) cPosent.item(0);
            NodeList plodent = elementRegAlta.getElementsByTagName("Plodent");
            Element Plodent = (Element) plodent.item(0);
            NodeList provent = elementRegAlta.getElementsByTagName("Provent");
            Element Provent = (Element) provent.item(0);
            NodeList graTar = elementRegAlta.getElementsByTagName("GraTar");
            Element GraTar = (Element) graTar.item(0);
            NodeList mail = elementRegAlta.getElementsByTagName("Mail");
            Element Mail = (Element) mail.item(0);
            NodeList panc = elementRegAlta.getElementsByTagName("Panc");
            Element Panc = (Element) panc.item(0);

            AssignPhysicalCardResponse assignPhysicalCardResponse = new AssignPhysicalCardResponse();
            try {
                assignPhysicalCardResponse.setCodigo(getCharacterDataFromElement(Codigo));
                assignPhysicalCardResponse.setTimeStamp(getCharacterDataFromElement(TimeStamp));
                assignPhysicalCardResponse.setCodigoRespuesta(getCharacterDataFromElement(CodigoRespuesta));
                assignPhysicalCardResponse.setErrorCodigo(getCharacterDataFromElement(ErrorCodigo));
                assignPhysicalCardResponse.setErrorDescripcion(getCharacterDataFromElement(ErrorDescripcion));
                assignPhysicalCardResponse.setPais(getCharacterDataFromElement(Pais));
                assignPhysicalCardResponse.setRuc(getCharacterDataFromElement(Ruc));
                assignPhysicalCardResponse.setAlant(getCharacterDataFromElement(Alant));
                assignPhysicalCardResponse.setAlact(getCharacterDataFromElement(Alact));
                assignPhysicalCardResponse.setCallent(getCharacterDataFromElement(Callent));
                assignPhysicalCardResponse.setLocalent(getCharacterDataFromElement(Localent));
                assignPhysicalCardResponse.setCPosent(getCharacterDataFromElement(CPosent));
                assignPhysicalCardResponse.setPlodent(getCharacterDataFromElement(Plodent));
                assignPhysicalCardResponse.setProvent(getCharacterDataFromElement(Provent));
                assignPhysicalCardResponse.setGraTar(getCharacterDataFromElement(GraTar));
                assignPhysicalCardResponse.setMail(getCharacterDataFromElement(Mail));
                assignPhysicalCardResponse.setPanc(getCharacterDataFromElement(Panc));
            } catch (Exception e) {
                e.printStackTrace();
            }
            rd.close();
            return new AssignPhysicalCardResponse(assignPhysicalCardResponse.getCodigo(), assignPhysicalCardResponse.getTimeStamp(), assignPhysicalCardResponse.getCodigoRespuesta(), assignPhysicalCardResponse.getErrorCodigo(), assignPhysicalCardResponse.getErrorDescripcion(), assignPhysicalCardResponse.getPais(), assignPhysicalCardResponse.getRuc(), assignPhysicalCardResponse.getAlant(), assignPhysicalCardResponse.getAlact(), assignPhysicalCardResponse.getCallent(), assignPhysicalCardResponse.getLocalent(), assignPhysicalCardResponse.getCPosent(), assignPhysicalCardResponse.getPlodent(), assignPhysicalCardResponse.getProvent(), assignPhysicalCardResponse.getGraTar(), assignPhysicalCardResponse.getMail(), assignPhysicalCardResponse.getPanc());

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new MalformedURLException();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // parse XML
    private static Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
}
