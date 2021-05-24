package database;

import fuzzy.Label;
import fuzzy.LinguisticVariable;
import fuzzy.membership.GaussianFunction;
import fuzzy.membership.MembershipFunction;
import fuzzy.membership.TrapezoidalFunction;
import fuzzy.membership.TriangularFunction;
import fuzzy.quantifier.AbsoluteQuantifier;
import fuzzy.quantifier.Quantifier;
import fuzzy.universe.DenseUniverse;
import fuzzy.universe.DiscretteUniverse;
import fuzzy.universe.Universe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataInitialization {

    public static List<LinguisticVariable> initializeLinguisticVariables(JSONObject params) {
        List<LinguisticVariable> variables = new ArrayList<>();
        JSONArray jsonVariables = params.getJSONArray("LinguisticVariables");

        for (int i = 0; i < jsonVariables.length(); i++) {
            JSONObject jsonVariable = jsonVariables.getJSONObject(i);
            String vName = jsonVariable.getString("Name");

            JSONObject jsonUniverse = jsonVariable.getJSONObject("Universe");
            Universe universe = initializeUniverse(jsonUniverse);

            List<Label> labels = new ArrayList<>();
            JSONArray jsonLabels = jsonVariable.getJSONArray("Labels");

            for (int j = 0; j < jsonLabels.length(); j++) {
                JSONObject jsonLabel = jsonLabels.getJSONObject(j);

                String lName = jsonLabel.getString("Name");
                JSONObject jsonFunction = jsonLabel.getJSONObject("Function");

                MembershipFunction membershipFunction = initializeMemebershipFunction(jsonFunction);

                labels.add(new Label(lName,universe,membershipFunction));
            }

            variables.add(new LinguisticVariable(vName,labels));
        }

        return variables;
    }

    public static List<Quantifier> initializeAbsoluteQuantifiers(JSONObject params) {
        JSONObject jsonQuantifiers = params.getJSONObject("Quantifiers");
        JSONObject jsonAbsoluteQuantifiers = jsonQuantifiers.getJSONObject("Absolute");
        JSONArray jsonLabels = jsonAbsoluteQuantifiers.getJSONArray("Labels");

        List<Quantifier> quantifiers = new ArrayList<>();

        for (int i = 0; i < jsonLabels.length(); i++) {
            JSONObject jsonLabel = jsonLabels.getJSONObject(i);
            String lName = jsonLabel.getString("Name");

            JSONObject jsonFunction = jsonLabel.getJSONObject("Function");
            MembershipFunction membershipFunction = initializeMemebershipFunction(jsonFunction);
            Label label = new Label(lName,null,membershipFunction);

            quantifiers.add(new AbsoluteQuantifier(lName,label));
        }
        int a = 0;
        return quantifiers;
    }

    private static MembershipFunction initializeMemebershipFunction(JSONObject jsonFunction) {
        MembershipFunction membershipFunction = null;
        switch (jsonFunction.getString("Type")) {
            case "Trapezoidal": {
                double fLeftBottom = jsonFunction.getDouble("LeftBottom");
                double fLeftTop = jsonFunction.getDouble("LeftTop");
                double fRightTop = jsonFunction.getDouble("RightTop");
                double fRightBottom = jsonFunction.getDouble("RightBottom");
                membershipFunction = new TrapezoidalFunction(fLeftBottom, fLeftTop, fRightTop, fRightBottom);
                break;
            }
            case "Triangular": {
                double fLeft = jsonFunction.getDouble("Left");
                double fApex = jsonFunction.getDouble("Apex");
                double fRight = jsonFunction.getDouble("Right");
                membershipFunction = new TriangularFunction(fLeft, fApex, fRight);
                break;
            }
            case "Gaussian": {
                double fApex = jsonFunction.getDouble("Apex");
                double fSpread = jsonFunction.getDouble("Spread");
                membershipFunction = new GaussianFunction(fApex, fSpread);
                break;
            }
        }
        return membershipFunction;
    }

    private static Universe initializeUniverse(JSONObject jsonUniverse) {
        Universe universe = null;
        double left = jsonUniverse.getInt("Left");
        double right = jsonUniverse.getInt("Right");

        if (jsonUniverse.getBoolean("Discrette")) {
            universe = new DiscretteUniverse(left,right,1.0);
        } else {
            universe = new DenseUniverse(left,right,0.1);
        }

        return universe;
    }
}
