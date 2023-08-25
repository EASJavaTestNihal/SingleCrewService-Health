#!/bin/sh
echo "db = connect( 'mongodb://localhost/crewdb' );" > insertManyCrewMembers.js
echo "db.Crew.insertMany( [" >> insertManyCrewMembers.js
X=1
XEND=100
while [ $X -le $XEND ]
do
 if [ `expr ${X} % 100` -eq 0 ]
 then
  echo "        {Name: \"Member${X}\", Rank: \"Captain\", CrewID: ${X}}," >> insertManyCrewMembers.js
 elif [ `expr ${X} % 10` -eq 0 ]
 then
  echo "        {Name: \"Member${X}\", Rank: \"Officer\", CrewID: ${X}}," >> insertManyCrewMembers.js
 else 
  echo "        {Name: \"Member${X}\", Rank: \"Engineer\", CrewID: ${X}}," >> insertManyCrewMembers.js
 fi

 X=`expr $X + 1`
done 
echo "] )" >> insertManyCrewMembers.js
