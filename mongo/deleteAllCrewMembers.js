db = connect( 'mongodb://localhost/crewdb' );
db.Crew.deleteMany({})
