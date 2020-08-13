package com.example.recyclerview.api.data;

/**
 * @file .java
 * @brief Original IPC control facility definition
 * <p>
 * Copyright(C) Hitachi Information & Communication Engineering, Ltd. 2020. All Rights Reserved.
 */
public class HeaderData {

//  Message number between SSSU and receiver
  public char messageNumber;

//  SSSU-receiver processing number
  public char processingNumber;

//  The number that is set/referenced for the solution server to control retransmission.
  public short sequenceNumber;

//  Length of data part (number of bytes)
  public short dataLength;

  public HeaderData ( final char messageNumber, final char processingNumber, final short sequenceNumber, final short dataLength) {
    this.messageNumber = messageNumber;
    this.processingNumber = processingNumber;
    this.sequenceNumber = sequenceNumber;
    this.dataLength = dataLength;
  }

  public HeaderData ( final char messageNumber, final char processingNumber, final short dataLength) {
    this.messageNumber = messageNumber;
    this.processingNumber = processingNumber;
    this.dataLength = dataLength;
  }
}
