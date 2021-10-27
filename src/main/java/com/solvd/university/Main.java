package com.solvd.university;

import com.solvd.university.impl.EnrollmentServiceImpl;
import com.solvd.university.impl.InformationCommiteeServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws PersonInvalidDataException, IOException, URISyntaxException {

        Logger logger = LogManager.getLogger(Main.class);

        List<City> cities = new ArrayList<>();
        cities.add(new City("Minsk"));
        cities.add(new City("Pinsk"));
        cities.add(new City("Brest"));

        List<Department> departments = new ArrayList<>();

        List<Specialization> specializations = new ArrayList<>();
        try {
            specializations.add(new Specialization("Automation of technological processes and productions"));
            specializations.add(new Specialization("Intelligent devices, machines and production"));
            specializations.add(new Specialization("Computer mechatronics"));
            specializations.add(new Specialization("Design and operation of nuclear power plants"));
            specializations.add(new Specialization("Industrial thermal power engineering"));
            specializations.add(new Specialization("Relay protection sand automation"));
        } catch (SpecialisationInvalidDataException e) {
            logger.error("Exception when try to initialise specialisation array", e);
        } finally {
            logger.debug("Finish specialisation's array initialisation");
        }

        List<Specialization> automativeSpecialisations = new ArrayList<>();
        automativeSpecialisations.add(specializations.get(0));
        automativeSpecialisations.add(specializations.get(1));
        automativeSpecialisations.add(specializations.get(2));

        List<Specialization> mechEngineerSpec = new ArrayList<>();
        mechEngineerSpec.add(specializations.get(3));
        mechEngineerSpec.add(specializations.get(4));
        mechEngineerSpec.add(specializations.get(5));

        departments.add(new Department("Automative", automativeSpecialisations));
        departments.add(
                new Department(
                        "Mechanical Engineering",
                        mechEngineerSpec
                )
        );

        University university =
                new University("Garvard", new Date("03/04/1988"), cities.get(0), departments);

        List<SpecializationPlan> specializationPlans = new ArrayList<>();
        specializationPlans.add(new FullTimeSpecializationPlan(specializations.get(0), 10, 200));
        specializationPlans.add(new FullTimeSpecializationPlan(specializations.get(1), 0, 140));
        specializationPlans.add(new FullTimeSpecializationPlan(specializations.get(1), 40, 230));
        specializationPlans.add(new FullTimeSpecializationPlan(specializations.get(2), 20, 240));
        specializationPlans.add(new FullTimeSpecializationPlan(specializations.get(3), 15, 3000.21, 54, 300));
        specializationPlans.add(new DistanceSpecializationPlan(specializations.get(5), 50, 433.99));

        List<Certificate> certificates = new ArrayList<>();
        certificates.add(new CentralizeTestingCertificate(100000000, Subject.MATHEMATICS, 70));
        certificates.add(new CentralizeTestingCertificate(153663000, Subject.CHEMISTRY, 40));
        certificates.add(new CentralizeTestingCertificate(646577800, Subject.PHYSICS, 55));
        certificates.add(new HighSchoolCertificate(816247800, 7));

        Employee employee = null;
        Entrant entrant = null;
        try {
            employee = new Employee("Kamarouski", "Andrei", "Sergeevich", EmployeePosition.TEACHER);
            entrant = new Entrant("Kamarouski", "Andrei", "Sergeevich", LocalDate.of(1998, 4, 27));
        } catch (PersonInvalidDataException e) {
            logger.error("Entrant initialisation failed", e);
        } finally {
            logger.debug("Finish entrant initialisation");
        }
        EntrantForm bachelorEntrantForm =
                new BachelorEntrantForm(65, entrant, specializationPlans.get(0), true, employee, LocalDate.of(2021, 8, 2), certificates);

        EntrantForm masterEntrantForm =
                new MasterEntrantForm(
                        1355235,
                        entrant,
                        specializationPlans.get(2),
                        false,
                        employee,
                        LocalDate.of(2020, 6, 30),
                        specializations.get(1),
                        LocalDate.now()
                );

        EnrollmentService enrollmentService = new EnrollmentServiceImpl(specializationPlans);
        InformationCommiteeService informationCommiteeService = new InformationCommiteeServiceImpl();
        logger.info("Available specialisations:");
        logger.info(enrollmentService.getAvailableSpecialisations("distance").toString());

        try {
            Person dekan = new Employee("Kolesnikov", "Mikhail", EmployeePosition.MANAGER);
            Person abiturient = new Entrant("Pupkin", "Vasya", LocalDate.of(1999, 5, 26));
        } catch (PersonInvalidDataException e) {
            logger.error("Person data is not valid", e);
        } finally {
            logger.debug("Finish entrant initialisation");
        }

        logger.info("\n##### example of the operation of the control class #####");
        logger.info(informationCommiteeService.getEducationIntstituteInfo(university));
        logger.info(
                informationCommiteeService.getSpecializationPlanInfo(specializationPlans.get(3)));
        logger.info(informationCommiteeService.getPersonShortName(employee));

        logger.info("###### Interface using example ######");
        for (Accessible a : specializationPlans) {
            logger.info(String.format("Is free places accessible: %b", a.isFreePlacesAccessible()));
            logger.info(String.format("Is paid places accessible: %b", a.isPaidPlacesAccessible()));
        }

        boolean validationResult = informationCommiteeService.isValidDocument(bachelorEntrantForm);
        logger.info(String.format("Baachelor's entrant form is: %s", validationResult ? "VALID" : "INVALID"));

        logger.info(informationCommiteeService.askAboutCurrentDateTime(entrant));

        logger.info(String.format("Can entrable to high education: %b", enrollmentService.canEntrableToHighEducation(entrant)));

        logger.info("Abbreviation: " + informationCommiteeService.getAbbreviation(university));

        logger.info("Example with try with resourcces");
        try (Unnessesary unnessesary = new Unnessesary()) {
            logger.debug("Do something in try with resources");
        }

        logger.info("##### Generics using example #####");
        Folder<EntrantForm> entrantFormFolder2021 = new Folder<>();
        entrantFormFolder2021.addDocument(bachelorEntrantForm);
        entrantFormFolder2021.addDocument(masterEntrantForm);

        Map<Employee, String> employeeAnswers = new HashMap<>();
        employeeAnswers.put(new Employee("Vasya", "Pupkin", EmployeePosition.TEACHER), "We can upgrade our computers");
        employeeAnswers.put(new Employee("Inna", "Ivanovna", EmployeePosition.MANAGER), "We can improve our skills");
        Survey<Employee> employeesSurvey = new Survey<>("What can be improved", "All)", employeeAnswers);
        logger.debug(employeesSurvey.getResults());

        Set<FullTimeSpecializationPlan> fullTimeSpecializationPlans = new HashSet<>();
        fullTimeSpecializationPlans.add(new FullTimeSpecializationPlan(specializations.get(4), 40, 3));
        fullTimeSpecializationPlans.add((FullTimeSpecializationPlan) specializationPlans.get(1));
        FinalEntrantPlan<FullTimeSpecializationPlan> fullTimePlan2021 = new FinalEntrantPlan<>(fullTimeSpecializationPlans, LocalDate.of(2021, 1, 1), employee);
        logger.debug(fullTimePlan2021.getYearPlan());

        logger.info("######### Apache FileUtils StringUtils example #########");

        Book harryPotterBook = Book.getInstance();
        harryPotterBook.setFileName("J. K. Rowling - Harry Potter 1 - Sorcerer's Stone.txt");
        harryPotterBook.setGenre(Genre.FANTASTIC);

        Map<String, Integer> fileWords = new HashMap<>();
        URI harryPotterPath = Objects.requireNonNull(Main.class.getClassLoader().getResource(harryPotterBook.getFileName())).toURI();
        File harryPotterFile = new File(Objects.requireNonNull(harryPotterPath));
        Pattern wordPattern = Pattern.compile("[a-zA-Z]+");

        List<String> lines = FileUtils.readLines(harryPotterFile, "UTF-8");
        for (String line : lines) {
            Matcher matcher = wordPattern.matcher(line);
            while (matcher.find()) {
                String word = StringUtils.lowerCase(matcher.group());
                Integer count = fileWords.get(word);
                if (Objects.isNull(count)) {
                    count = 0;
                }
                fileWords.put(word, ++count);
            }
        }

        Map<String, Integer> sortedWords = fileWords.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        File outputFile = new File("ofiles/words_length_info.txt");
        OutputStream outputStream = FileUtils.openOutputStream(outputFile);
        for (Map.Entry<String, Integer> word : sortedWords.entrySet()) {
            String outputLine = String.format("%s\t- %d times\n", word.getKey(), word.getValue());
            outputStream.write(outputLine.getBytes(StandardCharsets.UTF_8));
            logger.info(outputLine);
        }
    }
}
