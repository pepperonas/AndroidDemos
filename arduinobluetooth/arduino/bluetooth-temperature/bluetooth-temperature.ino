
#include "DHT.h"

#define DHTPIN 9
#define DHTTYPE DHT22 //DHT11, DHT21, DHT22

DHT dht(DHTPIN, DHTTYPE);


char data = 0;                //Variable for storing received data (bt)

void setup()
{
  Serial.begin(9600);         //Sets the data rate in bits per second (baud) for serial data transmission

  Serial.println("Setting up bluetooth and temperature sensor.");

  pinMode(13, OUTPUT);        //Sets digital pin 13 as output pin

  dht.begin();               // Sets up the temperature sensor
}

void loop()
{

  // BT
  if (Serial.available() > 0) // Send data only when you receive data:
  {
    data = Serial.read();      //Read the incoming data and store it into variable data
    Serial.print(data);        //Print Value inside data in Serial monitor
    Serial.print("\n");        //New line
    if (data == '1')           //Checks whether value of data is equal to 1
      digitalWrite(13, HIGH);  //If value is 1 then LED turns ON
    else if (data == '0')      //Checks whether value of data is equal to 0
      digitalWrite(13, LOW);   //If value is 0 then LED turns OFF
  }


  // TEMPERATURE
  float h = dht.readHumidity(); //Luftfeuchte auslesen
  float t = dht.readTemperature(); //Temperatur auslesen

  // Prüfen ob eine gültige Zahl zurückgegeben wird. Wenn NaN (not a number) zurückgegeben wird, dann Fehler ausgeben.
  if (isnan(t) || isnan(h))
  {
    Serial.println("DHT22 konnte nicht ausgelesen werden");
  }
  else
  {
    //Serial.print("Luftfeuchte: ");
    //Serial.print(h);
    //Serial.print(" %\t");
    //Serial.print("Temperatur: ");
    Serial.print(t);
    Serial.println("C");
  }
}
