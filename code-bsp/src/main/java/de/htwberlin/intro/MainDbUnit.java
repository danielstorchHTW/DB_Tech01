package de.htwberlin.intro;

import java.net.URL;
import java.sql.SQLException;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.htwberlin.test.utils.DbUnitUtils;
import de.htwberlin.utils.DbCred;

public class MainDbUnit {
  private static final Logger L = LoggerFactory.getLogger(MainDbUnit.class);
  private static IDatabaseTester dbTester;
  private static IDatabaseConnection dbTesterCon = null;
  private static String dataDirPath = "de/htwberlin/test/data/";
  private static URL dataFeedUrl = ClassLoader.getSystemResource(dataDirPath);
  private static IDataSet feedDataSet = null;

  public static void main(String[] args) throws SQLException {
    L.info("Start");
    try {
      dbTester = new JdbcDatabaseTester(DbCred.driverClass, DbCred.url, DbCred.user, DbCred.password, DbCred.schema);
      m2();
    } catch (Exception e) {
      DbUnitUtils.closeDbUnitConnectionQuietly(dbTesterCon);
      throw new RuntimeException(e);
    }
    L.info("Ende");
  }
  
  static void m1() throws Exception {
    dbTesterCon = dbTester.getConnection();
    IDataSet dataSet = new CsvURLDataSet(ClassLoader.getSystemResource(dataDirPath));
    dbTester.setDataSet(dataSet);
    DatabaseOperation.CLEAN_INSERT.execute(dbTesterCon, dataSet);
  }
  
  static void m2() throws Exception {
    L.info("Start");
    feedDataSet = new CsvURLDataSet(dataFeedUrl);
    dbTester.setDataSet(feedDataSet);
    ITable table = feedDataSet.getTable("Raum");
    int noOfRows = table.getRowCount();
    for (int i = 0; i < noOfRows; i++) {
      Object sampleId = table.getValue(i, "RaumNr");
      System.out.println(sampleId);
    }
    L.info("Ende");
  }


}
