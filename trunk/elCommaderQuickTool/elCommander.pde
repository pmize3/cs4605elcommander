/*
  AnalogReadSerial
 Reads an analog input on pin 0, prints the result to the serial monitor 
 
 This example code is in the public domain.
 */
#include "el.h"
unsigned char incomingByte = 0;
int j = 0;
CCommand * command;
void setup() {
  Serial.begin(9600);
}
void loop() {
  //if (Serial.available() > 0) {
    
    if(incomingByte == 0xfb)
    {
    incomingByte = Serial.read();
    pinMode(13, OUTPUT);
 digitalWrite(13, HIGH);
    incomingByte = Serial.read();
    command = decode(incomingByte);
    int ret = execute(command);
    j++;
    Serial.print(ret, DEC);

    free(command);
  }
}

CCommand * decode(unsigned char b)
{
  CCommand * temp = (CCommand *)malloc(sizeof(CCommand));
  if( temp != NULL )
  {
    temp->analog = (b & 0x80) >> 7;
    temp->dataDirection = (b & 0x40) >> 6;
    temp->val = (b & 0x20) >> 5;
    temp->index = (b & 0x1f);
    
    if( temp->analog )
    {
      if( temp->index > 5 )
        temp->index = 5;
      //Serial.print("Analog ");
    }
    else
    {
        if( temp->index > 13 )
          temp->index = 13;
      //Serial.print("Digital ");
    }
    if( temp->dataDirection )
    {
      //Serial.print("read ");
    }
    else
    {
      //Serial.print("write of vale ");
      //Serial.print(temp->val, DEC);
      //Serial.print(" ");
    }
    //Serial.print("to pin ");
    //Serial.print(temp->index, DEC);
  }
  return temp;
}

int execute(CCommand * command)
{
  int ret = 0;
  if( command != NULL )
  {
    
    if( !command->analog ) // Digital pin
    {
      if( command->dataDirection ) // Read
      {
        pinMode(command->index, INPUT);
        ret = digitalRead(command->index);
      }
      else
      {
        pinMode(command->index, OUTPUT);
        digitalWrite(command->index, command->val);
        ret = command->val;
      }
    }
    else
    {
      ret = analogRead(command->index);
      Serial.read();
      Serial.read();
    }
     
  }
  
  return ret;
}
