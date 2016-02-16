# Big data course project

### DATASET

We used [STANDFORD Snap Dataset](http://snap.stanford.edu/data/loc-gowalla.html) which you can download from [here](http://snap.stanford.edu/data/loc-gowalla_totalCheckins.txt.gz)

_"Gowalla is a location-based social networking website where users share their locations by checking-in. The friendship network is undirected and was collected using their public API, and consists of 196,591 nodes and 950,327 edges. We have collected a total of 6,442,890 check-ins of these users over the period of Feb. 2009 - Oct. 2010."_

### SAMPLE

| [user]  | [check-in time]		  | [latitude]	    | [longitude]	  | [location id]|
| ------- |:---------------------:|:---------------:|:---------------:|:------------:|
| 196514  | 2010-07-24T13:45:06Z  | 53.3648119      | -2.2723465833   |145064		 |
| 196514  | 2010-07-24T13:44:58Z  | 53.360511233    | -2.276369017    |1275991		 |
| 196514  | 2010-07-24T13:44:46Z  | 53.3653895945   | -2.2754087046   |376497		 |
| 196514  | 2010-07-24T13:44:38Z  | 53.3663709833   | -2.2700764333   |98503		 |
| 196514  | 2010-07-24T13:44:26Z  | 53.3674087524   | -2.2783813477   |1043431		 |
| 196514  | 2010-07-24T13:44:08Z  | 53.3675663377   | -2.278631763    |881734		 |
| 196514  | 2010-07-24T13:43:18Z  | 53.3679640626   | -2.2792943689   |207763		 |
| 196514  | 2010-07-24T13:41:10Z  | 53.364905       | -2.270824       |1042822		 |

### TOOLS

I used Apache Spark with Scala. At first I tried MongoDB because Gowalla Dataset is from Swarm. This company uses MongoDB. However, I decided to use Spark because it was more understandable for me.

### PROBLEMS

**1-) Who are the top 100 users checked-in on dataset?**

**2-) What are the top 100 places checked-in on dataset?**

**3-) Getting top users checked-in a place in which a user checked-in ?**

### ANSWERS

**1-)** At first we need to parse the dataset for getting the _"userid"_ column, so we use regex _"\\t+"_ and get five columns. Then we use **take()** function and get the first column only. **take(1)** means get only first one column. If we use **take(2)**, it takes first two columns. After this operation we use map-reduce. Spark has not _sortByValue_ function. Therefore we need to swap values and use *sortByKey*. It means after reduce operation , we swap key and value then we call map function again. Also our new key is our value. Then with **sortByKey(false)** we make our descending list. **take(100)** function takes top 100 users of the list.

**2-)** At first we need to parse the dataset for getting the _"locationid"_ column, so we use regex _"\\t+"_ and get five columns. We can't use ~~take()~~ function here. Because if we use ~~take(4)~~, it takes the first four columns but we need only the "locationid" column. So we use **“line.split("\\t+")(4)”**, it takes the fourth element of line after split. The rest operations is same like by first question.

**3-a)** already solved... solution explanation will be updated..

### MONGODB SOLUTION
If you want to learn how to do, check [my friend's repository](https://github.com/ozcaan11/mongodb-pymongo-bigdata).