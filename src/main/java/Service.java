import org.apache.thrift.TException;
import servise.JsReferenceService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service implements JsReferenceService.Iface {

    String PATH="C:\\Users\\Alex\\IdeaProjects\\SOAP_GUI_APP\\JsReference";
    public List<String> getSections(){
        List<String> sectionList = new ArrayList<>();
        File mainDir = new File(PATH);
        for (File file : Objects.requireNonNull(mainDir.listFiles()))
            sectionList.add(file.getName());
        return sectionList;
    }

    public List<String> getSubsections(String sectionName) {
        List<String> strings = new ArrayList<>();
        for (String str : getSections())
            if (str.equals(sectionName)) {
                File file = new File(PATH+"\\" + sectionName);
                for (File file1 : Objects.requireNonNull(file.listFiles())) {
                    strings.add(file1.getName());
                }
            }
        return strings;
    }

    public String getSubsectionInfo(String sectionName, String subsectionName){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            File file = new File(PATH + "\\" + sectionName + "\\" + subsectionName);
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                stringBuilder.append(strLine + "\n");
            }
            fstream.close();
            br.close();
        } catch (IOException e){
            System.out.println("Error");
        }
        return stringBuilder.toString();
    }

    public void deleteSubsection(String sectionName, String subsectionName){
        File file = new File(PATH+"\\" + sectionName + "\\" + subsectionName);
        file.delete();
    }

    public void setInfoInSubsection(String sectionName, String subsectionName, String newText){
        File file = new File(PATH+"\\" + sectionName + "\\" + subsectionName);
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        printWriter.print(newText);
        printWriter.close();
    }

    public void addSubsection(String sectionName, String subsectionName){
        File file = new File(PATH+"\\" + sectionName + "\\" + subsectionName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void addSection(String sectionName) {
        File file = new File(PATH+"\\" + sectionName);
        file.mkdir();
    }
}
