package ch.hsr.servicestoolkit.solver;

import java.util.Collections;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hsr.servicestoolkit.model.Model;
import ch.hsr.servicestoolkit.repository.ModelRepository;

@Component
@Path("/engine/solver")
public class SolverEndpoint {

	private final Logger log = LoggerFactory.getLogger(SolverEndpoint.class);
	private final ModelRepository modelRepository;

	@Autowired
	public SolverEndpoint(ModelRepository modelRepository) {
		this.modelRepository = modelRepository;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{modelId}")
	@Transactional
	public Set<BoundedContext> solveModel(SolverConfiguration config, @PathParam("modelId") Long id) {
		Model model = modelRepository.findOne(id);
		if (model == null || config == null) {
			return Collections.emptySet();
		}

		GephiSolver solver = new GephiSolver(model, config);
		Set<BoundedContext> result = solver.solveWithMarkov();
		log.info("model {} solved, found bounded contexts: {}", model.getName(), result.toString());
		return result;
	}

}