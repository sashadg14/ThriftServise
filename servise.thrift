namespace java src.main.java.servise

struct Subsection {
    1: string name
    2: string info
}

struct Section {
   1 : string name
   2 : list<string> subsectionList
}

struct JsRefference{
    1: list<Section> sectionsList
    2: Author author
}

struct Author{
    1: string firstName
    2: string secondName
    3: string speciality
}

service JsReferenceService {

        JsRefference getJsRefferense(),

        Subsection getSubsection(1:string sectionName,2: string subsectionName),

        void deleteSubsection(1: string sectionName, 2: string subsectionName),

        void updateSubsection(1: string sectionName,2: Subsection subsection),

        void addSubsection(1: string sectionName, 2: Subsection subsection),

        void addSection(1: Section section),

}