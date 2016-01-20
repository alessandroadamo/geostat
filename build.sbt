name := "geostat"

version := "0.1"
val mahoutVersion = "0.11.1"

libraryDependencies ++= Seq(
	
	"org.apache.spark" %% "spark-core" % "1.6.0" % "provided",
	"org.apache.spark" %% "spark-mllib" % "1.6.0" % "provided",
  	
	// Mahout's Spark libs
	"org.apache.mahout" %% "mahout-math-scala" % mahoutVersion,
	"org.apache.mahout" %% "mahout-spark" % mahoutVersion
	exclude("org.apache.spark", "spark-core_2.10"),
	"org.apache.mahout"  % "mahout-math" % mahoutVersion,
	"org.apache.mahout"  % "mahout-hdfs" % mahoutVersion
	exclude("com.thoughtworks.xstream", "xstream")
	exclude("org.apache.hadoop", "hadoop-client")
)

resolvers += Resolver.mavenLocal
