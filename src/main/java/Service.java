import org.apache.thrift.TException;
import servise.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service implements JsReferenceService.Iface {

    String PATH="C:\\Users\\Alex\\IdeaProjects\\SOAP_GUI_APP\\JsReference";

    @Override
    public JsRefference getJsRefferense() throws TException {
        return new JsRefference(getSections(),new Author("Alex","Mamayko","Artificial Intelligence"));
    }

    public List<Section> getSections(){
        List<Section> sectionList = new ArrayList<>();
        File mainDir = new File(PATH);
        for (File file : Objects.requireNonNull(mainDir.listFiles())) {
            sectionList.add(new Section(file.getName(),getSubsectionsList(file.getName())));
        }
        return sectionList;
    }
    private List<String> getSectionsStr() {
        List<String> sectionList = new ArrayList<>();
        File mainDir = new File(PATH);
        for (File file : mainDir.listFiles())
            sectionList.add(file.getName());
        return sectionList;
    }

    private List<String> getSubsectionsList(String sectionName){
        List<String> strings = new ArrayList<>();
        for (String section : getSectionsStr())
            if (section.equals(sectionName)) {
                File file = new File(PATH+"\\" + sectionName);
                for (File file1 : Objects.requireNonNull(file.listFiles())) {
                    strings.add(file1.getName());
                }
            }
        return strings;
    }
    @Override
    public Subsection getSubsection(String sectionName, String subsectionName) throws TException {
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
        return new Subsection(sectionName,stringBuilder.toString());
    }

    public void deleteSubsection(String sectionName, String subsectionName){
        File file = new File(PATH+"\\" + sectionName + "\\" + subsectionName);
        file.delete();
    }

    @Override
    public void updateSubsection(String sectionName, Subsection subsection) throws TException {
        File file = new File(PATH+"\\" + sectionName + "\\" + subsection.getName());
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        printWriter.print(subsection.getInfo());
        printWriter.close();
    }

    @Override
    public void addSubsection(String sectionName, Subsection subsection) throws TException {
        File file = new File(PATH+"\\" + sectionName + "\\" + subsection.getName());
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @Override
    public void addSection(Section section) throws TException {
        File file = new File(PATH+"\\" + section.getName());
        System.out.println(file.getPath());
        file.mkdir();
    }


}
