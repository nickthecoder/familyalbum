To install into tomcat, the following section needs to be added to tomcat's server.xml.
Also, you need to place the database connector jar into tomcats common directory.



cp /gidea/projects/mysqlConnector/3.0.11/install/mysql-connector-java-3.0.11-stable-bin.jar common/lib


        <!-- **** familyalbum **** -->
        <Context path="/familyalbum"
          docBase="/home/nick/projects/familyalbum/install/webapp" >

          <Resource name="jdbc/familyalbum" scope="Shareable" type="javax.sql.DataSource"/>
            <ResourceParams name="jdbc/familyalbum">
                <parameter>
                    <name>factory</name>
                    <value>org.apache.commons.dbcp.BasicDataSourceFactory</value>
                </parameter>

                <!-- DBCP database connection settings -->
                <parameter>
                    <name>url</name>
                    <value>jdbc:mysql://giddyserv/familyalbum</value>
                </parameter>
                <parameter>
                    <name>driverClassName</name><value>com.mysql.jdbc.Driver</value>
                </parameter>
                <parameter>
                    <name>username</name>
                    <value>webclient</value>
                </parameter>
                <parameter>
                    <name>password</name>
                    <value>webpassword</value>
                </parameter>

                <!-- DBCP connection pooling options -->
                <parameter>
                    <name>maxWait</name>
                    <value>3000</value>
                </parameter>
                <parameter>
                    <name>maxIdle</name>
                    <value>100</value>
                </parameter>
                <parameter>
                    <name>maxActive</name>
                    <value>10</value>
                </parameter>
            </ResourceParams>
        </Context>


