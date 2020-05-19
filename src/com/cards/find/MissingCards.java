/*
 * @author: Karan Shah
 * @description: Find Missing Poker Cards
 * @input: Provide input file with deck and number separated by space
 * @output: Missing poker cards 
 * @run: hadoop jar jar_file_name main_class_name input_file_name ouput_directory
 */

package com.cards.find;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.util.ArrayList;

public class MissingCards {

    public static class MapClass extends Mapper<LongWritable, Text, Text, IntWritable> {
        /* Mapper Function */
        public void MapClass(LongWritable lw, Text txt, Context cnt) throws IOException, InterruptedException {
        	String str = txt.toString();
        	String[] split = str.split(" ");
        	Text text = new Text(split[0]);
        	IntWritable in = new IntWritable(Integer.parseInt(split[1]));
        	cnt.write(text,in);
        }
    }

    public static class ReduceClass extends Reducer<Text, IntWritable, Text, IntWritable> {   
    	/* Reducer Function */
    	public void ReduceClass(Text txt, Iterable<IntWritable> iw, Context cnt)throws IOException, InterruptedException {
    		int i = 1, cardPresent = 0;
    		ArrayList<Integer> suitePack = new ArrayList<Integer>();
         
        	for(i = 1; i <= 13; ++i) {
        		suitePack.add(i);
        	}
	        for(IntWritable cards : iw) {
	        	cardPresent = cards.get();
	        	if(suitePack.contains(cardPresent)) {
	        		suitePack.remove(suitePack.indexOf(cardPresent));
	        	}
	        }
	        for(i = 0; i < suitePack.size(); ++i) {
	        	cnt.write(txt, new IntWritable(suitePack.get(i)));
	        }
    	}  
    }

    public static void main(String[] args)throws Exception {
    	Configuration config = new Configuration();
        Job job = new Job(config, "com.cards.find.MissingCards");
        job.setJarByClass(MissingCards.class);
        job.setMapperClass(MapClass.class);
        job.setReducerClass(ReduceClass.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}