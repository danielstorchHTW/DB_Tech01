package de.htwberlin.raum;

import java.sql.Connection;

public interface IRaumService {
  void setConnection(Connection connection);
  Integer findAnzahlPlaetzeInRaum(int rid);
}