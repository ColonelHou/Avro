package com.sanss.hadoop.readWrite;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

public class Ser {
	
	public static void write(Schema schema) throws IOException
	{
		GenericRecord user1 = new GenericData.Record(schema);
		user1.put("name", "Alyssa");
		user1.put("favorite_number", 256);
		// Leave favorite color null

		GenericRecord user2 = new GenericData.Record(schema);
		user2.put("name", "Ben");
		user2.put("favorite_number", 7);
		user2.put("favorite_color", "red");
		
		File file = new File("users.avro");
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
		dataFileWriter.create(schema, file);
		dataFileWriter.append(user1);
		dataFileWriter.append(user2);
		dataFileWriter.close();
	}
	
	public static void read(Schema schema, File strFile) throws IOException
	{
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(strFile, datumReader);
		GenericRecord user = null;
		while (dataFileReader.hasNext()) {
		// Reuse user object by passing it to next(). This saves us from
		// allocating and garbage collecting many objects for files with
		// many items.
		user = dataFileReader.next(user);
		System.out.println(user); 
		}

	}

	public static void main(String[] args) throws IOException {
		
		String strWriteFile = "users.avro";
		String strReadFile = "user.avsc";
		Schema schema = new Schema.Parser().parse(new File(strReadFile));
        
		read(schema, new File(strWriteFile));
		
	}
}
