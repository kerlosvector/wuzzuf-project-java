package Wuzuf_Jobs_Project;

import org.apache.spark.sql.*;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;


import java.awt.*;
import java.io.IOException;
import java.util.List;


/**
 * Demonstrates analysing CSV data by executing SQL like queries in Apache Spark SQL.
 */

                        
public class CSVFileAnalysis {
    public static void main(String[] args) throws IOException {
        // Create Spark Session to create connection to Spark
        final SparkSession sparkSession = SparkSession.builder ().appName ("Spark CSV Analysis Demo").master ("local[2]")
                .getOrCreate ();

        // Get DataFrameReader using SparkSession
        final DataFrameReader dataFrameReader = sparkSession.read ();
        // Set header option to true to specify that first row in file contains
        // name of columns
        dataFrameReader.option ("header", "true");
        Dataset <Row> wuzzufJobsdf= dataFrameReader.csv( "C:\\Users\\Jesus Son\\Downloads\\Wuzzuf_Project (1)\\Wuzzuf_Project\\src\\main\\resources\\Wuzzuf_Jobs.csv" );


        // Print Schema to see column names, types and other metadata
        wuzzufJobsdf.printSchema ();
        System.out.println (wuzzufJobsdf.count ());
        System.out.println ("========================================================================");


        // Create view and execute query to convert types as, by default, all columns have string types
        wuzzufJobsdf.createOrReplaceTempView ("Jobs_details_RAW");
        Dataset<Row> jobDetailsData =  wuzzufJobsdf.dropDuplicates("Title",  "Company","Location","Type",
                "Level","YearsExp", "Country","Skills");

        // Print Schema to see column names, types and other metadata
        jobDetailsData.printSchema ();

        // Create view to execute query to get filtered data
        jobDetailsData = jobDetailsData.withColumn( "Title",jobDetailsData.col( "Title" ).cast( "String" ))
                .filter(jobDetailsData.col( "Title" ).isNotNull());
        jobDetailsData=jobDetailsData.withColumn( "Company",jobDetailsData.col( "Company" ).cast( "String" ))
                .filter(jobDetailsData.col( "Company" ).isNotNull());
        jobDetailsData=jobDetailsData.withColumn( "Location",jobDetailsData.col( "Location" ).cast( "String" ))
                .filter(jobDetailsData.col( "Location" ).isNotNull());
        jobDetailsData=jobDetailsData.withColumn( "Type",jobDetailsData.col( "Type" ).cast( "String" ))
                .filter(jobDetailsData.col( "Type" ).isNotNull());
        jobDetailsData=jobDetailsData.withColumn( "Level",jobDetailsData.col( "Level" ).cast( "String" ))
                .filter(jobDetailsData.col( "Level" ).isNotNull());
        jobDetailsData=jobDetailsData.withColumn( "YearsExp",jobDetailsData.col( "YearsExp" ).cast( "String" ))
                .filter(jobDetailsData.col( "YearsExp" ).isNotNull());
        jobDetailsData=jobDetailsData.withColumn( "Country",jobDetailsData.col( "Country" ).cast( "String" ))
                .filter(jobDetailsData.col( "Country" ).isNotNull());
        jobDetailsData=jobDetailsData.withColumn( "Skills",jobDetailsData.col( "Skills" ).cast( "List" ))
                .filter(jobDetailsData.col( "Skills" ).isNotNull());

        jobDetailsData.printSchema();
        jobDetailsData.describe(  ).show();



        // Viewing the most Popular Job Titles:
        jobDetailsData.createOrReplaceTempView ("PopularJobTitles");
        Dataset <Row> PopularJobTitles = sparkSession.sql ("SELECT Title, COUNT(Title) AS popular_titles" +
                "FROM PopularJobTitles" +
                "GROUP BY Title" +
                "ORDER BY popular_titles DESC" );

        PopularJobTitles.printSchema();
        PopularJobTitles.describe().show();

        // Preparing data for bar chart:
        List<String> JobTitle = PopularJobTitles.select("Title").as(Encoders.STRING()).collectAsList();
        List<Long> Popularity = PopularJobTitles.select("popular_titles").as(Encoders.LONG()).collectAsList();

        // Creating Chart:
        CategoryChart barChart_JobTitles = new CategoryChartBuilder().width(1024).height(768).title("Popular Job Titles").build();
        // Customize Chart
        barChart_JobTitles.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        barChart_JobTitles.getStyler ().setHasAnnotations (true);
        barChart_JobTitles.getStyler ().setStacked (true);

        barChart_JobTitles.addSeries("Popular Job Titles", JobTitle, Popularity);
        new SwingWrapper (barChart_JobTitles).displayChart ();

        BitmapEncoder.saveBitmap(barChart_JobTitles, "src/main/resources/JobTitlePopularity", BitmapEncoder.BitmapFormat.PNG);



        // Viewing the Number of Jobs per Company:
        jobDetailsData.createOrReplaceTempView ("JobsPerCompany");
        Dataset <Row> JobsPerCompany = sparkSession.sql ("SELECT Title, COUNT(Title) AS NumberOfJobs" +
                "FROM JobsPerCompany" +
                "GROUP BY Title " +
                "ORDER BY popular_titles DESC" );

        JobsPerCompany.printSchema();
        JobsPerCompany.describe().show();

        // Preparing data for pie chart:
        List<String> CompanyNames = JobsPerCompany.select("Company").as(Encoders.STRING()).collectAsList();
        List<Long> PopularTitles = JobsPerCompany.select("NumberOfJobs").as(Encoders.LONG()).collectAsList();

        // Create Chart:
        PieChart pieChart_NumOfJobs = new PieChartBuilder().width(800).height(600).title("Jobs Per Company").build();
        for (int i=0; i<CompanyNames.size(); i++){
            pieChart_NumOfJobs.addSeries(CompanyNames.get(i), PopularTitles.get(i));
        }
        // Customize Chart
        Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};
        pieChart_NumOfJobs.getStyler ().setSeriesColors (sliceColors);
        new SwingWrapper(pieChart_NumOfJobs).displayChart ();


        BitmapEncoder.saveBitmap(pieChart_NumOfJobs, "src/main/resources/JobsPerCompany", BitmapEncoder.BitmapFormat.PNG);



        // Viewing the most Popular Areas :
        jobDetailsData.createOrReplaceTempView ("PopularAreas");
        Dataset<Row> PopularAreas= sparkSession.sql ("SELECT Location, COUNT(Location) AS popular_areas" +
                "FROM PopularArea " +
                "GROUP BY Location" +
                "ORDER BY popular_areas DESC " +
                "LIMIT    1");

        PopularAreas.printSchema();
        PopularAreas.describe().show();

        // Preparing data for bar chart:
        List<String> Areas = PopularAreas.select("Location").as(Encoders.STRING()).collectAsList();
        List<Long> NumbOfRepAreas = PopularAreas.select("popular_areas").as(Encoders.LONG()).collectAsList();

        // Creating Chart:
        CategoryChart barChart_Areas = new CategoryChartBuilder().width(1024).height(768).title("Popular Areas").build();
        // Customize Chart
        barChart_Areas.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        barChart_Areas.getStyler ().setHasAnnotations (true);
        barChart_Areas.getStyler ().setStacked (true);

        barChart_Areas.addSeries("Popular Areas", Areas, NumbOfRepAreas);
        new SwingWrapper (barChart_Areas).displayChart ();

        BitmapEncoder.saveBitmap(barChart_Areas, "src/main/resources/PopularAreas", BitmapEncoder.BitmapFormat.PNG);




        // Viewing the Skills and their count:
        jobDetailsData.createOrReplaceTempView ("Skills");
        Dataset<Row> Skills= sparkSession.sql ( "SELECT substring_index(Skills, ',', -1)  AS SkillsTextRenamed"+
                "COUNT(substring_index(Skills, ',', -1)) AS SkillsCountRenamed"+
                "FROM Skills"+
                "GROUP BY SkillsTextRenamed"+
                "ORDER BY SkillsTextRenamed DESC");

        Skills.printSchema();
        Skills.describe().show();

    }
}
