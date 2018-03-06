namespace java src.main.java.servise

service JsReferenceService {

        list<string> getSections(),

        list<string>getSubsections(1:string section),

        string getSubsectionInfo(1:string sectionName,2: string subsectionName),

        void deleteSubsection(1: string sectionName, 2: string subsectionName),

        void setInfoInSubsection(1: string sectionName,2: string subsectionName,3: string newText),

        void addSubsection(1: string sectionName, 2: string subsectionName),

        void addSection(1: string sectionName),

}